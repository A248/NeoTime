# NeoTime
A base-ten system of time measurement. It is a superior alternative to the traditional 365-day year.

**Introduction**

The old-fashioned clock is outdated. It multiplies by arbitrary values (60, 24, 7, 52) to convert between kinds of dates. This leads to all sorts of unnecessary confusion and calculation. Why follow such odd conversions?

NeoTime is the solution. This new system of time measurement maintains the *day* as the universal unit of time while providing simple conversions between different classifications of time using a base-ten framework: There are 10 seconds in a minute, 10 minutes in an hour, 10 hours in a day, 10 days in a week, 10 weeks in a month, and 10 months in a year.

A neocentisecond (ncs) is the common unit of time for NeoTime users.
<br>To get a sense of NeoTime, you may rely on:
<br>1 ncs = 0.864 old seconds

**Basic Usage: Developers**

* Use `NeoTime.currentTimeMillis()` to get the current time as a long value.
* Use the `NeoDate` class to replace `java.util.Date`
* Use the `NeoInstant` class to replace `java.time.Instant`
