/*
 * SSLR Squid Bridge
 * Copyright (C) 2010 SonarSource
 * sonarqube@googlegroups.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.squidbridge.recognizer;

import com.google.common.collect.Lists;

import java.util.List;

public class CodeRecognizer {

  private final LanguageFootprint language;
  private final double threshold;

  public CodeRecognizer(double threshold, LanguageFootprint language) {
    this.language = language;
    this.threshold = threshold;
  }

  public final double recognition(String line) {
    double probability = 0;
    for (Detector pattern : language.getDetectors()) {
      probability = 1 - (1 - probability) * (1 - pattern.recognition(line));
    }
    return probability;
  }

  public final List<String> extractCodeLines(List<String> lines) {
    List<String> codeLines = Lists.newArrayList();
    for (String line : lines) {
      if (recognition(line) >= threshold) {
        codeLines.add(line);
      }
    }
    return codeLines;
  }

  public final boolean isLineOfCode(String line) {
    return recognition(line) - threshold > 0;
  }

}
