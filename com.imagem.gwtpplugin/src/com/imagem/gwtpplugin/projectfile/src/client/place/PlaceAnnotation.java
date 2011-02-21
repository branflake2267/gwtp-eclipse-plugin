/**
 * Copyright 2011 Les Syst�mes M�dicaux Imagem Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.imagem.gwtpplugin.projectfile.src.client.place;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;

import com.imagem.gwtpplugin.projectfile.ProjectClass;

public class PlaceAnnotation extends ProjectClass {

	private static final String A_BINDING_ANNOTATION = "com.google.inject.BindingAnnotation";
	private static final String A_TARGET = "java.lang.annotation.Target";
	private static final String A_RETENTION = "java.lang.annotation.Retention";
	private static final String E_FIELD = "java.lang.annotation.ElementType.FIELD";
	private static final String E_PARAMETER = "java.lang.annotation.ElementType.PARAMETER";
	private static final String E_METHOD = "java.lang.annotation.ElementType.METHOD";
	private static final String E_RUNTIME = "java.lang.annotation.RetentionPolicy.RUNTIME";
	
	public PlaceAnnotation(IPackageFragmentRoot root, String fullyQualifiedName) throws JavaModelException {
		super(root, fullyQualifiedName);
	}
	
	public PlaceAnnotation(IPackageFragmentRoot root, String packageName, String elementName) throws JavaModelException {
		super(root, packageName, elementName);
		if(type == null) {
			cu.createPackageDeclaration(packageName, null);
			
			cu.createImport(A_BINDING_ANNOTATION, null, null);
			String contents = "@BindingAnnotation\n";

			cu.createImport(A_TARGET, null, null);
			cu.createImport(E_FIELD, null, Flags.AccStatic, null);
			cu.createImport(E_PARAMETER, null, Flags.AccStatic, null);
			cu.createImport(E_METHOD, null, Flags.AccStatic, null);
			contents += "@Target({ FIELD, PARAMETER, METHOD })\n";

			cu.createImport(A_RETENTION, null, null);
			cu.createImport(E_RUNTIME, null, Flags.AccStatic, null);
			contents += "@Retention(RUNTIME)\n";
			
			contents += "public @interface " + elementName + " {}\n";
			
			type = cu.createType(contents, null, false, null);
		}
	}
}
