package com.epam.am.xml.model;

public class Paper {
    private String title;
    private Type type;
    private boolean isMonthly;
    private Chars chars;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isMonthly() {
        return isMonthly;
    }

    public void setMonthly(boolean monthly) {
        isMonthly = monthly;
    }

    public Chars getChars() {
        return chars;
    }

    public void setChars(Chars chars) {
        this.chars = chars;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "title='" + title + '\'' +
                ", type=" + type +
                ", isMonthly=" + isMonthly +
                ", chars=" + chars +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paper)) return false;

        Paper paper = (Paper) o;

        if (isMonthly != paper.isMonthly) return false;
        if (title != null ? !title.equals(paper.title) : paper.title != null) return false;
        if (type != paper.type) return false;
        return chars != null ? chars.equals(paper.chars) : paper.chars == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (isMonthly ? 1 : 0);
        result = 31 * result + (chars != null ? chars.hashCode() : 0);
        return result;
    }

    public enum Type {
        NEWSPAPER, MAGAZINE, BOOKLET
    }

    public static class Chars {
        private boolean isColored;
        private int pageCount;
        private String index;
        private boolean isGlossy;

        public boolean isColored() {
            return isColored;
        }

        public void setColored(boolean colored) {
            isColored = colored;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public boolean isGlossy() {
            return isGlossy;
        }

        public void setGlossy(boolean glossy) {
            isGlossy = glossy;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Chars)) return false;

            Chars chars = (Chars) o;

            if (isColored != chars.isColored) return false;
            if (pageCount != chars.pageCount) return false;
            if (isGlossy != chars.isGlossy) return false;
            return index != null ? index.equals(chars.index) : chars.index == null;

        }

        @Override
        public int hashCode() {
            int result = (isColored ? 1 : 0);
            result = 31 * result + pageCount;
            result = 31 * result + (index != null ? index.hashCode() : 0);
            result = 31 * result + (isGlossy ? 1 : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Chars{" +
                    "isColored=" + isColored +
                    ", pageCount=" + pageCount +
                    ", index='" + index + '\'' +
                    ", isGlossy=" + isGlossy +
                    '}';
        }
    }
}
