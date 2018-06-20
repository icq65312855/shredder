package com.shredder.spelling;

public interface WordsFinder {
    /**
     * find word in accordance with segment
     * @param segment
     * @return true - if word is available in the global dictionary, false - otherwise
     */
    boolean wordAvailable(String segment);
}
