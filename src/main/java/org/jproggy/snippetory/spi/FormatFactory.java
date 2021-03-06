/*******************************************************************************
 * Copyright (c) 2011-2012 JProggy.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * EXCEPT AS EXPRESSLY SET FORTH IN THIS AGREEMENT, THE PROGRAM IS PROVIDED ON AN 
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, EITHER EXPRESS OR 
 * IMPLIED INCLUDING, WITHOUT LIMITATION, ANY WARRANTIES OR CONDITIONS OF TITLE, 
 * NON-INFRINGEMENT, MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE
 *******************************************************************************/

package org.jproggy.snippetory.spi;

import org.jproggy.snippetory.TemplateContext;

/**
 * A FormatFactory is registered to create a new formatting attribute.
 * <p>
 * Format definition in templates is done  by attributes. Every format has exactly one main
 * attribute and an arbitrary number of sub-attributes. The name of sub-attributes starts
 * with the name of the main attribute followed by a dot and the name of the sub-attribute.
 * That way every format has it's own name space.
 * </p>
 * <p>
 * To be able to use a format in Snippetory one has to register FormatFactory able to create
 * it. The creation process of a {@link Format} consists of three steps. First an instance of
 * {@code FormatFactory} is registered at {@link Format#REGISTRY}. The FormatFactory
 * creates a {@link FormatConfiguration}. The  FormatConfiguration in turn will get the 
 * sub-attributes provided via setter-methods. The FormatConcigutation is stored, and
 * every time a new Location is copied the configuration will be asked for a format instance.
 * </p>
 * 
 * @author B. Ebertz
 *  
 */
public interface FormatFactory {
	/**
	 * Instantiate the {@link FormatConfiguration} to be kept in the mate data of a template
	 * and will be used for creating real template nodes. For each real template node
	 *  {@link FormatConfiguration#getFormat(TemplateNode)} is called exactly once.
	 *  However, there is always one node that never gets used to render a template, but only
	 *  for cloning all the others.
	 * 
	 * @param definition the attribute value from the template 
	 * @param ctx the TemplateContext provides additional information like the locale
	 * @return a FormatConfiguration that will be kept as meta data of this node
	 */
	FormatConfiguration create(String definition, TemplateContext ctx);	
}
