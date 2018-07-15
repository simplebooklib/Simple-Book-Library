package com.library.support;

import com.library.constant.Constant;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * 문자형 코드 정보를 enum 객체로 변경하는 Converter
 */
@Component
public class StringValueToEnumConverterFactory implements ConverterFactory<String, Enum<? extends Constant>> {

	public <T extends Enum<? extends Constant>> Converter<String, T> getConverter(
			Class<T> paramClass) {
		return new StringToEnumsConverter<>(paramClass);
	}

	private final class StringToEnumsConverter<T extends Enum<? extends Constant>> implements Converter<String, T> {

		private final Class<T> enumType;

		public StringToEnumsConverter(Class<T> enumType) {
			this.enumType = enumType;
		}

		public T convert(String param) {
			T[] enumConstants = enumType.getEnumConstants();
			for (T enumConstant : enumConstants) {
				if (enumConstant.name().equalsIgnoreCase(param)) {
					return enumConstant;
				}
			}
			return null;
		}
	}

}
