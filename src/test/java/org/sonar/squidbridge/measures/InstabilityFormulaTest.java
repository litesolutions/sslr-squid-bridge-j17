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
package org.sonar.squidbridge.measures;

import org.junit.Test;
import org.sonar.squidbridge.api.SourcePackage;

import static org.junit.Assert.assertEquals;

public class InstabilityFormulaTest {

  InstabilityFormula distance = new InstabilityFormula();
  SourcePackage measurable = new SourcePackage("pac1");

  @Test
  public void calculateBestStability() {
    measurable.setMeasure(Metric.CA, 50);
    measurable.setMeasure(Metric.CE, 0);
    assertEquals(0, measurable.getDouble(Metric.INSTABILITY), 0.01);
  }

  @Test
  public void calculateWorstStability() {
    measurable.setMeasure(Metric.CA, 0);
    measurable.setMeasure(Metric.CE, 10);
    assertEquals(1, measurable.getDouble(Metric.INSTABILITY), 0.01);
  }

  @Test
  public void calculateOnIsolatedProject() {
    measurable.setMeasure(Metric.CA, 0);
    measurable.setMeasure(Metric.CE, 0);
    assertEquals(0, measurable.getDouble(Metric.INSTABILITY), 0.01);
  }

}
