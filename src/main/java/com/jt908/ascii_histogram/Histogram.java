package com.jt908.ascii_histogram;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class Histogram {

    private static final String BAR_CHAR = "█";
    private String keyWidth = "30";
    private String valueWidth = "100";
    private boolean descending = true;

    public Histogram() {
    }
    
    public Histogram(String keyWidth, String valueWidth, boolean descending) {
        this.keyWidth = keyWidth;
        this.valueWidth = valueWidth;
        this.descending = descending;
    }

    public String draw(Map<?, Integer> map, String title, Optional<String> units) {
        Integer highestValue = map.values().stream().max(Integer::compareTo).get();
        StringBuilder graph = new StringBuilder();
        graph.append(String.format("* %s *\n--------------------------------\n", title));
        for (Map.Entry<?, Integer> entry : sortEntriesByValue(map)) {
            Object key = entry.getKey();
            Integer value = entry.getValue();
            String bars = "";

            float percentage = (value.floatValue() / highestValue.floatValue()) * 100;
            for (int i = 0; i < Math.round(percentage); i++) {
                bars += BAR_CHAR;
            }
            if (units.isPresent()) {
                graph.append(String.format(
                        "%-{keyWidth}.{keyWidth}s | %-{valueWidth}.{valueWidth}s %d (%s)\n"
                                .replace("{keyWidth}", keyWidth).replace("{valueWidth}", valueWidth),
                        key, bars, value, units.get()));
            } else {
                graph.append(String.format("%-{keyWidth}.{keyWidth}s | %-{valueWidth}.{valueWidth}s %d\n"
                        .replace("{keyWidth}", keyWidth).replace("{valueWidth}", valueWidth), key, bars, value));
            }
        }
        return graph.toString();
    }

    private <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> sortEntriesByValue(Map<K, V> map) {
        SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
                int res = e1.getValue().compareTo(e2.getValue());
                if (descending) {
                    res = -res;
                }
                return res != 0 ? res : 1;
            }
        });
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

    public String getKeyWidth() {
        return keyWidth;
    }

    public void setKeyWidth(String keyWidth) {
        this.keyWidth = keyWidth;
    }

    public String getValueWidth() {
        return valueWidth;
    }

    public void setValueWidth(String valueWidth) {
        this.valueWidth = valueWidth;
    }

    public boolean isDescending() {
        return descending;
    }

    public void setDescending(boolean descending) {
        this.descending = descending;
    }
}