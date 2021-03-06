/*
 * Copyright 2014 Artur.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jaxygen.typeconverter.converters;

import java.util.ArrayList;
import java.util.List;
import org.jaxygen.collections.PartialList;
import org.jaxygen.dto.collections.PaginableListResponseBaseDTO;
import org.jaxygen.typeconverter.ClassToClassTypeConverter;
import org.jaxygen.typeconverter.TypeConverterFactory;
import org.jaxygen.typeconverter.exceptions.ConversionError;
import org.jodah.typetools.TypeResolver;

/** Converts the PaginableListResponseBaseDTO into PartialList.
 * Not that converter uses registered converter for changing elements of PaginableListResponseDTO to elements of
 * ParialList.
 * 
 * @author Artur
 * @param <FROM> FROM paginable.
 * @param <TO> TO partial.
 */
public abstract class PaginableToPartialConverter<FROM extends PaginableListResponseBaseDTO, TO extends PartialList>
        extends ClassToClassTypeConverter<FROM, TO> {

    private final TypeConverterFactory converters;

    /**Create PaginableToPartialConverter that uses named converters factory (see @TypeConverterFactory)
     * 
     * @param typeConvertersFactoryName Name of used converters factory.
     */
    protected PaginableToPartialConverter(final String typeConvertersFactoryName) {
        this.converters = TypeConverterFactory.instance(typeConvertersFactoryName);
    }
    
    /**Create paginable partial converter that uses the default TypeConverterFactory
     * 
     */
    protected PaginableToPartialConverter() {
        this.converters = TypeConverterFactory.instance();
    }

    public TO convert(final FROM from) throws ConversionError {
        TO rc;
        try {
            rc = to().newInstance();
        } catch (InstantiationException ex) {
            throw new ConversionError("Could not create an instace of retun class while converting paginable collections", ex);
        } catch (IllegalAccessException ex) {
            throw new ConversionError("Could not create an instace of retun class while converting paginable collections", ex);
        }
        Class<?>[] types = TypeResolver.resolveRawArguments(PartialList.class, rc.getClass());
        Class<?> toContentClass = (Class<?>) types[0];
        if (from.getElements() != null) {
            List<Object> arrayList = new ArrayList<Object>(from.getElements().size());
            for (Object in : from.getElements()) {
                Object out = converters.convert(in, toContentClass);
                arrayList.add(out);
            }
            rc.addAll(arrayList);
            rc.setTotalSize(from.getSize());
        }
        return rc;
    }
}
