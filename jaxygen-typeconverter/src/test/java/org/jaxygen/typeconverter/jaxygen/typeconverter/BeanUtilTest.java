/*
 * Copyright 2016 Seba.
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

package org.jaxygen.typeconverter.jaxygen.typeconverter;

import java.math.BigDecimal;
import org.jaxygen.annotations.BeanTransient;
import org.jaxygen.typeconverter.util.BeanUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Sebastian Malinski <sebastian.malinski@xdsnet.pl>
 */
@RunWith(MockitoJUnitRunner.class)
public class BeanUtilTest {

  public static class A {
    
    private String stringField;
    private BigDecimal numericField;
    private String transientField;

    public String getStringField() {
      return stringField;
    }

    public void setStringField(String stringField) {
      this.stringField = stringField;
    }

    public BigDecimal getNumericField() {
      return numericField;
    }

    public void setNumericField(BigDecimal numericField) {
      this.numericField = numericField;
    }

    @BeanTransient
    public String getTransientField() {
      return transientField;
    }

    public void setTransientField(String transientField) {
      this.transientField = transientField;
    }
    
  }
  
  public static class B {
    
    private String stringField;
    private Double numericField;
    private Double transientField;

    public String getStringField() {
      return stringField;
    }

    public void setStringField(String stringField) {
      this.stringField = stringField;
    }

    public Double getNumericField() {
      return numericField;
    }

    public void setNumericField(Double numericField) {
      this.numericField = numericField;
    }

    public Double getTransientField() {
      return transientField;
    }

    public void setTransientField(Double transientField) {
      this.transientField = transientField;
    }
    
  }
  
  @Test
  public void test_shallTranslateBeanAtoB() {
    String testString = "test string";
    // given
    A aBean = new A();
    aBean.setStringField(testString);
    aBean.setNumericField(BigDecimal.TEN);
    B bBean = new B();
    
    // when
    BeanUtil.translateBean(aBean, bBean);
    
    // then
    Assert.assertEquals(testString, bBean.getStringField());
    Assert.assertEquals(10, bBean.getNumericField(), 0);
    Assert.assertNull(bBean.getTransientField());
  }
}
