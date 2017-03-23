/*
 *    ParamGraphCanvas.java
 *    Copyright (C) 2017 Otto-von-Guericke-University, Magdeburg, Germany
 *    @author Tim Sabsch (tim.sabsch@ovgu.de)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *    
 *    
 */
package moa.gui.visualization;

import java.awt.Color;
import java.awt.Dimension;

import moa.evaluation.MeasureCollection;

/**
 * ParamGraphCanvas is an implementation of AbstractGraphCanvas showing the
 * relation between a parameter and the measures.
 * 
 * @author Tim Sabsch (tim.sabsch@ovgu.de)
 * @version $Revision: 1 $
 * @see AbstractGraphCanvas
 */
public class ParamGraphCanvas extends AbstractGraphCanvas {

	private static final long serialVersionUID = 1L;
	
	private double[] variedParamValues;

	/**
	 * Initialises a ProcessGraphCanvas by calling the super constructor with a
	 * ParamGraphAxes as instance of AbstractGraphAxes and GraphScatter as
	 * instance of AbstractGraphPlot.
	 */
	public ParamGraphCanvas() {
		super(new ParamGraphAxes(), new GraphScatter());
	}

	/**
	 * Sets the scatter graph.
	 * 
	 * @param measures
	 *            information about the curves
	 * @param mSelect
	 *            currently selected measure
	 */
	public void setGraph(MeasureCollection[] measures, int mSelect, 
			String variedParamName, double[] variedParamValues, Color[] colors) {
		this.measures = measures;
		this.measureSelected = mSelect;
		this.variedParamValues = variedParamValues;
		((GraphScatter) this.plotPanel).setGraph(measures, mSelect, 
				variedParamName, variedParamValues, colors);
		updateCanvas(false);
	}
	
	@Override
	public double getMaxXValue() {
		double max = Double.MIN_VALUE;

		for (int i = 0; i < this.variedParamValues.length; i++) {
			if (this.variedParamValues[i] > max) {
				max = this.variedParamValues[i];
			}
		}
		return max;
	}

	@Override
	public void setSize() {
		setSize((int) (baseWidth  * x_resolution),
				(int) (baseHeight * y_resolution));
	}

	@Override
	public void setPreferredSize() {
		setPreferredSize(new Dimension(getWidth(), getHeight()));		
	}

}
