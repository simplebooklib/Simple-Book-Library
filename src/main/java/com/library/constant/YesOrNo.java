package com.library.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum YesOrNo implements Constant {
	YES("Y", "Yes"),
	NO("N", "No");
	private String code;
	private String desc;
}
