# Animation - CS 3500 hw05
This is an animator built for the purposes of hw05 in CS 3500 Summer I. It can parse text files
that contain information about the size of the animation frame, the shapes (rectangles or ellipses)
that will move across the frame, and the key frames these shapes will go through.

## Components of the Animator
All components of the animator that will display the animation is held in the cs3500.animator 
package: 
- The AnimationModel interface is responsible for keeping track of the animation board, all
shapes on the board, and the frames the shapes will move through at given ticks, and tracking the
universal tick the animation is currently at.
- The AnimationController interface is responsible for controlling the progression of the
animation, and delivering the information the view needs from the model.
- The AnimationView interface is responsible for displaying the model in the chosen view type: 
  - visual: implemented with DrawingPanel, a JPanel, and VisualAnimationView in the 
  `cs3500.animator.view.visual` package. This view outputs the animation inside a new window.
  - text: implemented with TextAnimationView inside the `cs3500.animator.view.textual` package. 
  This view outputs a textual representation of the animation to the given text file supplied as a 
  command-line argument, or prints it to `System.out` by default.
  - svg: implemented with SVGAnimationView inside the `cs3500.animator.view.textual` package. 
  This view outputs a svg-format representation of the animation to the given file supplied as a 
  command-line argument, or prints it to `System.out` by default.

## Animation Model
The board that will display the animation is held in the animation package. The board will hold all
shapes, control the progression of the animation, and interface with the viewer. This board will be
comprised of a set of `Movable` shapes can move on the fixed background of the animation board. It
also contains a universal ticker entitled `AnimationModelImpl.tick` which will control the 
application of `Motion`s generated from a shape's `KeyFrame`s to `Movable` shapes. This animation 
board has a model, `AnimationModel`, which will maintain the state of the board. It has a view, 
`AnimationView`, which will be responsible for interfacing with the viewer: displaying the 
animation, displaying clickable buttons to play, pause, or otherwise alter the progression of the 
animation, and methods to take in user inputs to add shapes to the board. To also has a controller 
that will run the animation, interpret user inputs accepted from the viewer, and will relay the 
appropriate commands to the view or the model.

Class Invariants:
- No `Movable`s in the `AnimationModel`'s set of shapes may be null.
- The tick for the model will always be equal to or greater than 0.
- The height and width of the board will always be greater than or equal to 0.
- The board will have a traditional coordinate system with the origin at the top left of the frame.

### Movable
The `Movable` interface represents shapes that will appear on the animation board. A shape will 
have a coordinate position of its center relative to the coordinates on the `AnimationModel`, a 
positive height and width, and an rgba color. The way it is drawn will be determined by the 
concrete implementation of this class. For example `MovableEllipseImpl` will draw an ellipse, 
whereas `MovableRectangleImpl` will draw a rectangle. 

Implementations:
- Rectangle:
  - has x and y coordinates representing the top left corner
  - has a width and height representing the geometric width and height of the rectangle
- Ellipse
  - has x and y coordinates representing the center
  - has a width and height representing the diameter (horizontal and vertical)

Class Invariants:
- No `KeyFrames` in the `AnimationModel`'s set of frames may be null.
- The list of frames must not overlap in their ticks (a Keyframe cannot at the same tick as 
another), and must be in chronological order.
- A movable's height and width must be positive
- All concrete implementations must have a type which is an enum
`src.movable.AbstractMovableImpl.movableType`

#### KeyFrame
The `KeyFrame` interface represents key frames that a `Movable` will pass through. This frame 
contains values for the object: x and y coordinates, height and width, color, and the
tick at which it occurs. KeyFrames can be transformed into Motions, and then applied to a `Movable` 
with the move method which will move the given `Movable` according to the current tick value of 
`AnimationModelImpl.tick`. For example:
```
Take two KeyFrames kf1 and kf2 which occur at tick = 1 and tick = 5 respectively. Then when a 
Motion is made with these two frames, it exists from 1 to 5, with starting values of kf1, and 
ending values of kf2. Consider this created Motion mot for the following examples.

At tick = 0, if the t1 of a Motion mot is 1, then mot.move(Movable mov) will do nothing.

When the tick falls in the range [mot.t1, mot.t2], mot.move(Movable mov) will place mov
at the appropriate position, scale it the appropriate amount, and change the color to the correct 
rgba value. 

If the current tick is 4, and the t1 of mot is 1, then mot.move(Movable mov) will set mov's
x and y positions to (mot.x1 + (change in s per tick for mot) * (tick - mot.t1))
```
Class Invariants:
- The height and width values in a `KeyFrame` must be greater than or equal to 0.
- The tick must be greater than or equal to 0.
- All of the color values in a `KeyFrame` must be in the range `[0, 255]`.

#### Motion **DEPRECATED**
THIS INTERFACE IS ONLY USED FOR ITS move(Movable movable) METHOD
MOVEMENTS ARE NOW STORED AS LISTS OF KeyFrame's

The `Motion` interface represents motions that a `Movable` can go through. This motion will contain
starting and ending values for the object: x and y coordinates, height and width, color, and the
ticks at which it will begin and end. Motions can be applied to a `Movable` with the move method 
which will move the given `Movable` according to the current tick value of 
`AnimationModelImpl.tick`. For example:
```
At tick = 0, if the t1 of a Motion mot is 1, then mot.move(Movable mov) will do nothing.

When the tick falls in the range [mot.t1, mot.t2], mot.move(Movable mov) will place mov
at the appropriate position, scale it the appropriate amount, and change the color to the correct 
rgba value. 

If the current tick is 4, and the t1 of mot is 1, then mot.move(Movable mov) will set mov's
x and y positions to (mot.x1 + (change in s per tick for mot) * (tick - mot.t1))
```
Class Invariants:
- The height and width values in a `Motion` must be greater than or equal to 0.
- The ending tick must be greater than or equal to the starting tick, and the starting tick must be
greater than or equal to 0.
- All of the color values in a `Motion` must be in the range `[0, 255]`.

##AnimationFeatures
The AnimationFeatures, in the `cs3500.animator.view.visual` package, is responsible for providing
the view with a set of commands that can be given to its controller. These commands include some
that mutate the model (ex. adding a Movable shape), and others that simply alter the running of
the animation playback (ex. play/pause and changing the tick-rate). 

## AnimationController
EXTENDS AnimationFeatures INTERFACE
The AnimationController, in the `cs3500.animator.controller` package is responsible for relaying
data from the model to the methods in view that generate output to the viewer. Additionally it has
a method called run that outputs the animation in the chosen view format. 

When constructing a controller, it is necessary to give it a model, 
and a view of the types `AnimationModel`, and `AnimationView` respectively. Upon creation
it sets itself as the controller of its view by using the 
`setController(AnimationController controller)` method, and gets back a tick-rate to run the 
animation at.

## AnimationView
THe AnimationView, in the `cs3500.animator.view` package is responsible for output to the viewer. 
The type of view for an animation is decided via command-line arguments upon running the animator's
main class `Excellence.java`. There are three types: visual, text, and svg. These views output a 
video of the animation in a new window, a text representation of the animation (which is in the 
correct  format to initialize another animation with `AnimationReader`) to the chosen output 
location, or an SVG format text representation of the animation to the chosen output location, 
respectively.

Implementations:
- VisualAnimationView
  - The visual view has a tick-rate chosen via command-line argument, and may not output a textual
  representation of the animation anywhere. If the `render()` method is called on a 
  `VisualAnimationView` the view will throw an `UnsupportedOpperationException`. The animation is
  played in a new JPanel window once, and can be exited at any time, at which point the animator
  will stop.
- VisualEditorView
  - The editor view has all of the same qualities of the visual view, along with a panel of
  controls to add or remove Movable shapes from the animation, and to add add remove or edit 
  Key Frames from those Movable shapes.
- TextAnimationView
  - The text view has no tick-rate and will output the textual representation of the animation to 
  whichever `Appendable` output was chosen via command-line argument. The format of the text is as
  follows:
    - a line to declare the frame size and minimum x and y coordinates:   
    `canvas x y width height`. 
      - i.e. `canvas 23 -46 1000 450`
    - lines that declare shapes in the following format:  
    `shape name type`
      - i.e. `shape R4 rectangle`
    - lines that declare all motions for the shapes in the following format:  
    `motion shape-name tick1 x1 y1 width1 height1 r1 g1 b1  tick2 x2 y2 width2 height2 r2 g2 b2`
      - i.e. `motion disk1 1 190 180 20 30 0 49 90  1 190 180 20 30 0 49 90`
- SVGAnimationView
  - The svg format view has a tick-rate and will output the textual representation of the animation 
  to whichever `Appendable` output was chosen via command-line argument. The file or other output
  will be written in proper svg format, and can be played on any platform that supports SVG files.
  All rectangles are written as svg `<rect>`s and ellipses are svg `<ellipse>`s.

### VisualEditorView
This view is complex enough to require its own sub-section:
- Animation Panel
  - This is the portion of the editor that displays the visual view of the model. By default this
  panel plays the animation forwards on loop. The user may change the model, and the playback
  setting using the menu.
- Menu Panel
  - Playback Options - these do not mutate the model
    - play, pause: These are buttons available to play and pause the animation.
    - reverse: This is a checkbox which, when ticked, will cause the animation to play backwards
    - loop: This is a checkbox which, when ticked, will cause the animation to play on loop
    - speed: This is a text input and a button that allow the user to set the ticks per second of 
    the animation to the speed specified in the text field
  - Editing Options - these mutate the model
    - Add Shape: This is a button, which when pressed opens a pop-up window with the fields name,
    type, x, y, width, height, r, g, and b, which let the user add a shape of the given 
    specifications. Adding a shape will add it to the animation from tick = 0 to tick = 1.
    - Remove Shape: This is a button, which when clicked, opens a pop-up window that contains a 
    drop-down menu of all the shapes in the model. It allows the user to select one, then delete
    the shape. Doing so removes the shape from the model.
    - Add Key Frame: This is a button, which when clicked, opens a series of pop-up windows. First,
    a window opens prompting the user to select a shape from the model's list of shapes to
    determine which shape this new KeyFrame will be added to. Then it displays a window with text
    fields for all aspects of a KeyFrame: x, y, width, height, r, g, b and tick. When the user
    fills these in and presses Add Key Frame, the selected shape receives the new key frame.
    - Delete Key Frame: This is a button, which when clicked, opens a series of pop-up windows. 
    First, a window opens prompting the user to select a shape from the model's list of shapes to
    determine which shape a KeyFrame will be removed from. Then it displays a window with a
    drop-down menu with all of the key frames fro that shape. The user then selects a frame, clicks
    the Remove Key Frame button, and the frame is removed from that shape.
    - Edit Key Frame: This is a button, which when clicked, opens a series of pop-up windows. 
    First, a window opens prompting the user to select a shape from the model's list of shapes to
    determine which shape a KeyFrame will be edited from. Then it displays a window with a
    drop-down menu with all of the key frames from that shape. The user then selects a frame. 
    Finally, the user is shown a window with text fields for all aspects of a KeyFrame: x, y, 
    width, height, r, g, b and tick, as well as information about which shape and tick is being 
    edited. When the user fills these in and presses Edit Key Frame, the selected frame is changed
     accordingly to the entered values.

## Exceptions
There are exceptions that some methods throw in the event a disallowed action is performed. All of
these actions are listed below:
1.  `IllegalArgumentException` when an `AnimationController` is constructed with a null view or a 
null model
2.  `IllegalArgumentException` when an `Motion` is constructed with ticks less than 1, when the 
ending tick is less than the starting tick, the heights and widths are negative, or when the rgb 
values are negative
3.  `IllegalArgumentException` when `Motion.move(Movable movable)` is called with a null Movable
4.  `IllegalArgumentException` when an `Movable` is constructed if the given movableName is null, 
when the given list of motions are null, contain a null, are overlapping, or are not continuous, 
when color is null, or when the given height or width is negative.
5.  `IllegalArgumentException` when `Movable.buildMovable(String name, String type)` is called and 
the given shape type is not RECTANGLE or ELLIPSE
6.  `IllegalArgumentException` when `AnimationModel.clearMovablesIf(Predicate<Movable> predicate)` 
is called and the predicate is null
7.  `IllegalArgumentException` when an `AnimationModel` is constructed and the given height, width, 
or tick are negative, and when the given set of Movables is null, has repeat names or contains a 
null
8.  `IllegalStateException` when `AnimationModel.prevTick()` is called and the current tick is
already 0 (the minimum value)
9.  `IllegalStateException` when the source supplied to AnimationReader is in an incorrect format
10. `IllegalArgumentException` when an `AnimationView` is constructed and the given tick rate is not
greater than 0, or the output is null
11. `IllegalStateException` when the a `AbstractTextAnimationView` (text or svg) attempts to write
data to an output file and is somehow interrupted.
12. `IllegalArgumentException` when `DrawingPanel.paintComponent(Graphics g)` is called and the
given shape type is not RECTANGLE or ELLIPSE
13. `UnsupportedOpperationException` when `AnimationView.render()` is called on a 
`VisualAnimationView`.
14. `IllegalArgumentException` when the supplied command-line arguments are in the wrong format. 
The correct format of these command-line arguments can be found on 
[https://course.ccs.neu.edu/cs3500/hw_06_assignment.html#%28part._.The_main___method%29]
[CS3500 Assignment 6: The main() method].
15. `IllegalStateException` when the provided output file already exists, or if it is impossible to
create a `FileWriter` with the given file name.

## Running the tests
The tests for this animator are contained in the test folder, and are build on Junit 4.12. Each
test class is named after the class whose methods it tests. For example:

Mock model, view, and controllers are found in the test.animator folder which allow the testing of
inputs and outputs to and from the model or view via the controller. They have logs (StringBuilder)
that can be read to ensure each action is being executed and is initiating the appropriate domino 
effect in the model or view.
```
animator.AnimationModelImplTest.java is the tester class for AnimationModelImpl.java
```

## New Changes
- The representation for movement in shapes is changed from Motions to KeyFrames. This means only
the major turning points in a shape's trajectory are saved. When trying to move, these frames are
reverted to motions with an adaptor method, and the Motion interface's move method is called. This
allows the path of shapes to be easily and rapidly changed while not requiring a new method to be
implemented for KeyFrames.
- An editing view has been added, and its corresponding documentation can be found in the 
VisualEditorView subsection under the "AnimationView" section. This view allows the user to watch
and dynamically change the animation.
- The AnimationFeatures interface was added which outlines all of the possible inputs a view can
have on the model. The documentation for this new interface can be found in the 
"AnimationFeatures" section
- The AnimationController interface has been expanded to extent the new AnimationFeatures 
interface. This is to enforce that every controller for animations allows for communication to and
from the view. 

### Old Changes
- The AnimationController now runs the animation and relays all information from the model to a
view to render, rather than having the model directly output a String representation of the 
animation.
- Three AnimationView implementations have been added(textual, svg format, and visual). The
associated documentation about them is above in the "AnimationView" section.
- A main class has been added that instantiates the animation with two mandatory components: an
input text file, and a view type. The other optional arguments are an output where the the view
will write the animation representation if a text or svg view is chosen (the default is 
`System.out`), and the second is speed (the ticks per second of the animation) which is 1 tick per
second by default.

## Authors
* **Courtney, John, and Watwe, Aaditya**

[]: https://course.ccs.neu.edu/cs3500/hw_06_assignment.html#%28part._.The_main___method%299