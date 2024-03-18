package br.com.wells.core.domain.page;

import br.com.wells.core.util.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record Pagina<T>(List<T> list, long totalElements) {

	public <R> Pagina<R> map(Mapper<? super T, ? extends R> mapper) {
		List<R> mappedList = list == null ? new ArrayList<>()
				: list.stream().map(mapper::apply).collect(Collectors.toList());
		return new Pagina<>(mappedList, totalElements);
	}
}
