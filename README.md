# leave-balance-cli

[![Build Status][gh-actions-badge]][gh-actions] [![Clojure version][clojure-v]](project.clj)

Calculate PTO/leave balance at the end of the year and how much will be lost if none is taken.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Building

* Clone the project
* Build the uberjar

```bash
lein uberjar
```

## Usage

* Export your environment vars (or defaults are used)

  Example
  
  ```bash
  # Max leave you can carry over each year
  export LEAVE_MAX=150
  # Hours/week you gain
  export LEAVE_RATE=4
  # Current leave balance
  export LEAVE_BAL=200
  ```

* Run the application

  * Lein command

    ```bash
    lein run
    ```

  * Java command

    ```bash
    java -jar target/uberjar/leave_balance-0.1.0-SNAPSHOT-standalone.jar
    ```

## Examples

```bash
lein run

Current Leave Balance Info
-> Today is: 2020-05-14
-> Current balance: 200.0
-> Accrual rate (hours gained a week): 4.0
-> Weeks left to accrue leave: 33.00

End of Year Leave Balance Info
-> Est End of Year Total leave: 332.00
-> Max leave carry over: 150
-> Leave lost (if none taken): 182.00
-> Take this many hours of leave a week to avoid losing any: 5.52
```

## License

Copyright © 2020 Bill Howe

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
`http://www.eclipse.org/legal/epl-2.0`.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at `https://www.gnu.org/software/classpath/license.html`.

----

<!-- Named page links below: /-->

[gh-actions-badge]: https://github.com/wdhowe/leave-balance-cli/workflows/ci%2Fcd/badge.svg
[gh-actions]: https://github.com/wdhowe/leave-balance-cli/actions
[clojure-v]: https://img.shields.io/badge/clojure-1.10.0-blue.svg
