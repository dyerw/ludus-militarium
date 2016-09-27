
![ludus_logo.png](http://s14.postimg.org/bgo8gy5dd/ludus_logo.png)

## Overview

This is a turn-based strategy game engine for Clojure. It will be able to be used in two ways:

* With declarative scenario files using a basic and flexible ruleset
* By importing it as a library into your own Clojure projects to create more unique behavior

More docs are forthcoming but for now take a look at the `simple_scenario.cljs` file for an example of
custom scenarios.

## Demos!

[Video](https://www.youtube.com/watch?v=1eMvCR-IEc8&feature=youtu.be)

## Setup

To get an interactive development environment run:

    lein figwheel

and open your browser at [localhost:3449](http://localhost:3449/).
This will auto compile and send all changes to the browser without the
need to reload. After the compilation process is complete, you will
get a Browser Connected REPL. An easy way to try it is:

    (js/alert "Am I connected?")

and you should see an alert in the browser window.

To clean all compiled files:

    lein clean

To create a production build run:

    lein do clean, cljsbuild once min

And open your browser in `resources/public/index.html`. You will not
get live reloading, nor a REPL. 

## License

Copyright © 2016 Liam Dyer

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
