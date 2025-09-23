package com.showhive.common;

import java.util.List;

public record CursorPage<T>(
        List<T> contents,
        boolean hasNext
) {
}
