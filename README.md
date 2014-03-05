AECFacadeProcessingLibrary
==========================

A Processing library for drawing content on the Ars Electronica Center (AEC) media facade.

The library, called AECPlugin, allows you to use standard Processing draw commands to draw directly
on the Ars Electronica Center media facade. It creates all the necessary data packages and sends them
to a given destination IP/port, where the AEC Facade Simulator and Control software is running.

License
==========================
Copyright (C) 2013  Ars Electronica Futurelab

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

See LICENSE.md for details.

Installation
==========================

### Plugin Installation

Put the "AECPlugin" folder into you "libraries" subfolder in your Processing Sketchbooks folder.
(You can view and change this folder by selecting File > Preferences in Processing.)
Restart Processing if necessary.

Further instructions can be found here:
http://wiki.processing.org/w/How_to_Install_a_Contributed_Library


### Configuration

If you are running the AEC Facade Simulator and Control application on the same machine as the
processing sketch, simply start the Simulator, open one of the supplied Processing samples,
and press play - you will see the result of your interaction with the Processing canvas on
the Simulator's visualization. 

If the Simulator is on a different machine than the Processing sketch, open the file 
"config_user.ini" in the "data" subfolder of the sample sketch you want to run, and change the
"IP" entry to the address of the machine on which the Simulator is running.



Samples Description
===========================

There are 3 sample sketches of varying complexity included in the Samples folder.


### AECProcessingSample_SimpleRect

The most minimal example. The coordinates of your mouse cursor on the Processing canvas are converted to
Facade coordinates and a rectangle is drawn on the corresponding location.


### AECProcessingSample_Columns

Two coloured columns, one time-based, the other based on the mouse coordinate.


### AECProcessingSample_MousePulse

A time-based pulse that appears in regular intervals at the the mouse cursor's position.


### AECProcessingSample_Text

A simple example of a marquee scrolling across the main part of the AEC facade. It is written in a way that should make it easy to take and add it to other code. It is not recommended to vary font sizes or typefaces, but to use the existing font settings, which have been optimized for the particular facade raster.
