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
package org.jproggy.snippetory.engine.spi;

import java.util.Map;
import java.util.regex.Pattern;

import org.jproggy.snippetory.engine.Token.TokenType;

public class FluytCCSyntax extends FluytSyntax {

	@Override
	protected Map<Pattern, TokenType> createPatterns() {
		Map<Pattern, TokenType> patterns = super.createPatterns();
		String mock = "[ \t]*\\*/([^/\\*]*)/\\*[ \t]*";
		
		String field = "\\$(" + NAME + "\\((?:" + PLAIN_ATTRIBS + ")?)" + mock + "\\)";
		createFieldPattern(patterns, SyntaxVariant.Named, field);
		
		field = "\\$(\\(" + PLAIN_ATTRIBS + ")" + mock + "\\)";
		createFieldPattern(patterns, SyntaxVariant.Nameless, field);

		return patterns;
	}

	@Override
	protected String coatStart(TokenType type, SyntaxVariant variant) {
		return "(?:(?:/\\*|//)[ \t]*)?";
	}

	@Override
	protected String coatEnd(TokenType type, SyntaxVariant variant) {
		return "(?:[ \t]*(?:\\*/))?";
	}
}
