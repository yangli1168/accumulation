/**
 * 
 */
package com.xinqushi.constraint;

import org.springframework.binding.PropertyAccessStrategy;
import org.springframework.rules.constraint.property.AbstractPropertyConstraint;

/**
 * 
 */
public class DateFormatConstraint extends AbstractPropertyConstraint {

	
	@Override
	protected boolean test(PropertyAccessStrategy domainObjectAccessStrategy) {
		Object value = domainObjectAccessStrategy
				.getPropertyValue(getPropertyName());
		if (value == null) {// ������ֵΪ��ʱ���򲻽�����֤
			return true;
		}
		if (value instanceof String) {
			return value.toString().matches(
					"\\d{4}(\\-|\\/|\\.)\\d{1,2}(\\-|\\/|\\.)\\d{1,2}");
		} else {
			return false;
		}
	}

}
