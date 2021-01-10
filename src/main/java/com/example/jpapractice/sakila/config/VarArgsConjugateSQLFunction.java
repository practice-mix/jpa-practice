/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package com.example.jpapractice.sakila.config;

import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.Type;

import java.util.List;

/**
 * support mode:  match(xxx) against(x)    <br/>
 */
public class VarArgsConjugateSQLFunction implements SQLFunction {
	private final String begin;
	private final String sep;
	private final String middle;
	private final String end;
	private final Type registeredType;

	public VarArgsConjugateSQLFunction(Type registeredType, String begin, String sep, String middle, String end) {
		this.registeredType = registeredType;
		this.begin = begin;
		this.sep = sep;
		this.middle = middle;
		this.end = end;
	}

	@Override
	public boolean hasArguments() {
		return true;
	}

	@Override
	public boolean hasParenthesesIfNoArguments() {
		return true;
	}

	@Override
	public Type getReturnType(Type firstArgumentType, Mapping mapping) throws QueryException {
		return registeredType == null ? firstArgumentType : registeredType;
	}

	@Override
	public String render(Type firstArgumentType, List arguments, SessionFactoryImplementor factory) {
		final StringBuilder buf = new StringBuilder().append(begin);
		for (int i = 0; i < arguments.size() - 1; i++) {
			buf.append(transformArgument((String) arguments.get(i)));
			if (i < arguments.size() - 2) {
				buf.append(sep);
			}
		}
		buf.append(middle).append(arguments.get(arguments.size() - 1));
		return buf.append(end).toString();
	}

	protected String transformArgument(String argument) {
		return argument;
	}
}
