package com.showhive.admin.util;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PaginationUtils {
    public static class Offset {
        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class PageRequest {
            private int page = 0;
            private int size = 20;
            private String keyword;
            private SortOption sortOption;

            @Getter
            @Setter
            @NoArgsConstructor
            @AllArgsConstructor
            public static class SortOption {
                private String field;
                private Direction direction;

                public enum Direction {
                    ASC, DESC
                }
            }
        }

        @Getter
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        @AllArgsConstructor(access = AccessLevel.PROTECTED)
        public static class PageResponse<T> {
            private List<T> contents;
            private int page;
            private int size;
            private long totalElements;
            private int totalPages;
            private boolean hasNext;

            public static <T> PageResponse<T> of(List<T> contents, int page, int size, long totalElements) {
                int totalPages = (int) Math.ceil((double) totalElements / size);
                boolean hasNext = page + 1 < totalPages;

                return new PageResponse<>(contents, page, size, totalElements, totalPages, hasNext);
            }
        }
    }

    public static class Cursor {
        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class PageRequest {
            private String cursor;
            private int size;
            private String keyword;
            private SortOption sortOption;

            @Getter
            @Setter
            @NoArgsConstructor
            @AllArgsConstructor
            public static class SortOption {
                private String field;
                private Offset.PageRequest.SortOption.Direction direction;

                public enum Direction {
                    ASC, DESC
                }
            }
        }

        @Getter
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        @AllArgsConstructor(access = AccessLevel.PROTECTED)
        public static class PageResponse<T> {
            private List<T> contents;
            private String nextCursor;
            private boolean hasNext;
            private long totalCount;

            public static <T> PageResponse<T> of(
                    List<T> contents, String nextCursor, boolean hasNext, long totalCount
            ) {
                return new PageResponse<>(contents, nextCursor, hasNext, totalCount);
            }
        }
    }
}
