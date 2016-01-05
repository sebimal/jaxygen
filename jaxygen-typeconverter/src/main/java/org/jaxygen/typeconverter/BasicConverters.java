/*
 * Copyright 2016 Sebastian.
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

package org.jaxygen.typeconverter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import org.jaxygen.typeconverter.exceptions.ConversionError;

/**
 *
 * @author Sebastian Malinski <sebastian.malinski@xdsnet.pl>
 */
public class BasicConverters implements ConvertersRegistry {

  private final TypeConverter<String, char[]> stringToCharArrayConverter = new ClassToClassTypeConverter<String, char[]>() {
    @Override
    public char[] convert(String from) throws ConversionError {
      return from.toCharArray();
    }
  };
  
  private final TypeConverter<char[], String> charArrayToStringConverter = new ClassToClassTypeConverter<char[], String>() {
    @Override
    public String convert(char[] from) throws ConversionError {
      return new String(from);
    }
  };
  
  private final TypeConverter<String, BigDecimal> bigDecimalParser = new ClassToClassTypeConverter<String, BigDecimal>() {
    @Override
    public BigDecimal convert(String from) throws ConversionError {
      try {
        return stringToNumber(from, '.');
      } catch (ParseException ex) {
        throw new ConversionError("Could not parse BigDecimal from String", ex);
      }
    }
  };
  
  private final TypeConverter<BigDecimal, String> bigDecimalToStringConverter = new ClassToClassTypeConverter<BigDecimal, String>() {
    @Override
    public String convert(BigDecimal from) throws ConversionError {
      return from.toString();
    }
  };
  
  private final TypeConverter<BigDecimal, Double> bigDecimalToDoubleConverter = new ClassToClassTypeConverter<BigDecimal, Double>() {
    @Override
    public Double convert(BigDecimal from) throws ConversionError {
      return from.doubleValue();
    }
  };
  
  /**
   * Parses given number string to number using given sign as decimal separator.
   * @param numberString Number as string.
   * @param decimalSeparator Decimal separator used in string.
   * @return Number value of given string representation.
   * @throws ParseException 
   */
  public static BigDecimal stringToNumber(String numberString, char decimalSeparator) throws ParseException {
    if (numberString == null) {
      return BigDecimal.ZERO;
    }
    DecimalFormat nf = new DecimalFormat();
    DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    dfs.setDecimalSeparator(decimalSeparator);
    nf.setDecimalFormatSymbols(dfs);
    nf.setParseBigDecimal(true);
    return (BigDecimal) nf.parse(numberString);
  }
  
  @Override
  public TypeConverter[] getConverters() {
    return new TypeConverter[] {
      bigDecimalParser,
      bigDecimalToDoubleConverter,
      bigDecimalToStringConverter,
      charArrayToStringConverter,
      stringToCharArrayConverter
    };
  }

}
