# customlogpattern
Custom log pattern for slf4j to provide sub-millisecond timestamps.

We provide this when the pattern starts with **%{WEIRDO_MICROS}** and
we replace it with the ISO UTC timestamp to microsecond precision:
`HH:MM:SS.SSSSSSZ`

Add/replace corresponding lines in `log4j.properties`:

```
log4j.appender.console.layout=com.weirdocomputing.customlogpattern.WeirdoPatternLayout
log4j.appender.console.layout.conversionPattern=%d{WEIRDO_MICROS} %-5p %c{1} - %m%n
```
