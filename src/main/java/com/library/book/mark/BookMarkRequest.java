package com.library.book.mark;

import com.library.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Sort;

@Data
public class BookMarkRequest {

	@Range(min = 1, max = 50)
	private int page = 1;

	@Range(min = 1, max = 20)
	private int size = 10;

	private BookMarkSort sort;

	public Sort toSort() {
		Sort.Order order;
		if (this.sort == BookMarkSort.RECENCY) {
			order = new Sort.Order(Sort.Direction.DESC, "datetime");
		} else if (this.sort == BookMarkSort.TITLE_DESC) {
			order = new Sort.Order(Sort.Direction.DESC, "title");
		} else {
			order = new Sort.Order(Sort.Direction.ASC, "title");
		}
		return new Sort(order);
	}

	@Getter
	@AllArgsConstructor
	public enum BookMarkSort implements Constant {
		TITLE("title", "이름순"),
		TITLE_DESC("title_desc", "이름역순"),
		RECENCY("recency", "최신출판순");
		private String code;
		private String desc;
	}

}
