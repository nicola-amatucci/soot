/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Soot, a Java(TM) classfile optimization framework.                *
 * Copyright (C) 1997, 1998 Raja Vallee-Rai (kor@sable.mcgill.ca)    *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a project of the Sable Research Group,      *
 * School of Computer Science, McGill University, Canada             *
 * (http://www.sable.mcgill.ca/).  It is understood that any         *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * Java is a trademark of Sun Microsystems, Inc.                     *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other Sable Research Group projects, please      *
 * visit the web site: http://www.sable.mcgill.ca/                   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/*
 Reference Version
 -----------------
 This is the latest official version on which this file is based.

 Change History
 --------------
 A) Notes:

 Please use the following template.  Most recent changes should
 appear at the top of the list.

 - Modified on [date (March 1, 1900)] by [name]. [(*) if appropriate]
   [description of modification].

 Any Modification flagged with "(*)" was done as a project of the
 Sable Research Group, School of Computer Science,
 McGill University, Canada (http://www.sable.mcgill.ca/).

 You should add your copyright, using the following template, at
 the top of this file, along with other copyrights.

 *                                                                   *
 * Modifications by [name] are                                       *
 * Copyright (C) [year(s)] [your name (or company)].  All rights     *
 * reserved.                                                         *
 *                                                                   *

 B) Changes:

 - Modified on May 28, 1999 by Raja Vallee-Rai (rvalleerai@sable.mcgill.ca) (*)
   First release.
*/

package ca.mcgill.sable.soot;

import java.util.*;

public class MethodSignature
{
    String className;
    String methodName;
    List parameterTypes;
    Type returnType;
    
    int hashCode;
    
    public MethodSignature(String className, String methodName, 
        List parameterTypes, Type returnType)
    {
        this.className = className;
        this.methodName = methodName;
        this.parameterTypes = new ArrayList();
        this.parameterTypes.addAll(parameterTypes);
        this.returnType = returnType;
        
        int parameterHashCode = 0;
        
        for(int i = 0; i < this.parameterTypes.size(); i++)
            parameterHashCode = parameterHashCode * 1278433 + this.parameterTypes.get(i).hashCode();
            
        hashCode = className.hashCode() * 1001 + methodName.hashCode() * 
            13293 + returnType.hashCode() * 17;
    }

    public boolean equals(Object o)
    {
        if (o instanceof MethodSignature)
        {
            MethodSignature m = (MethodSignature)o;

            if (m.hashCode != hashCode) return false;

            return className.equals(m.className) && methodName.equals(m.methodName)
                && parameterTypes.equals(m.parameterTypes) && returnType.equals(m.returnType);
        }
        return false;
    }
    
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();

        buffer.append("<'" + className + "'");
        buffer.append(":'" + methodName + "'");
        buffer.append(":(");

        Iterator typeIt = parameterTypes.iterator();

        if(typeIt.hasNext())
        {
            buffer.append(typeIt.next());

            while(typeIt.hasNext())
            {
                buffer.append(",");
                buffer.append(typeIt.next());
            }
        }

        buffer.append(")");

        buffer.append(":" + returnType + ">");

        return buffer.toString();
    }

    public int hashCode()
    {
        return hashCode;
    }
}


