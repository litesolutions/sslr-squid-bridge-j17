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
package org.sonar.squidbridge.text;


abstract class LineContextHandler {

  abstract boolean matchToEnd(Line line, StringBuilder pendingLine);

  abstract boolean matchWithEndOfLine(Line line, StringBuilder pendingLine);

  abstract boolean matchToBegin(Line line, StringBuilder pendingLine);

  static boolean matchEndOfString(StringBuilder pendingLine, String end) {
    int pendingLineIndex = pendingLine.length() - end.length();
    if (pendingLineIndex < 0) {
      return false;
    }
    for (int endIndex = 0; endIndex < end.length(); endIndex++) {
      char endChar = end.charAt(endIndex);
      char pendingLineChar = pendingLine.charAt(pendingLineIndex + endIndex);
      if (endChar != pendingLineChar) {
        return false;
      }
    }
    return true;
  }

  static boolean matchEndOfString(StringBuilder pendingLine, char endChar) {
    if (pendingLine.length() < 1) {
      return false;
    }
    return pendingLine.charAt(pendingLine.length() - 1) == endChar;
  }

  static char getLastCharacter(StringBuilder pendingLine) {
    if (pendingLine.length() < 1) {
      throw new IllegalStateException("The pending line is empty.");
    }
    return pendingLine.charAt(pendingLine.length() - 1);
  }

}
