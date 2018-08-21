# java-ascii-histogram
Simple framework for generating ASCII histograms

```
Histogram histogram = new Histogram();
Map<String, Integer> map = new HashMap<>();
map.put("abcdef", 1);
map.put("ghijkl", 5);
map.put("mnopqr", 10);
System.out.println(histogram.draw(map, "MY TITLE", Optional.of("ms")));
```

```
* MY TITLE *
--------------------------------
mnopqr                         | █████████████████████████████████████████████████████████████████████ 10 (ms)
ghijkl                         | █████████████████████████████████                                     5 (ms)
abcdef                         | ██████                                                                1 (ms)
```
