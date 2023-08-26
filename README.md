# coffee-booking-app

Interface of an Android app which is used to book coffee.

This is made for my midterm. The UI closely followed the provided [Figma design](https://www.figma.com/file/DwWheBUCT8TKlhjdwMkDQ8/CS426?type=design&node-id=37-0&mode=design&t=NOuAESQZHkprNowk-0).

## Demo

(Press to go to youtube)

[![Demo video](https://img.youtube.com/vi/DZOCRP9_3LU/0.jpg)](https://www.youtube.com/watch?v=DZOCRP9_3LU)

## Technical

Made with Jetpack Compose.

Followed Google-recommended practices in building the app architecture, separation of UI layer and data layer, and gracefully handling of app navigation, state and data persistency.

No back-end. No data is stored on the machine.

### Architecture

`View models` is the only layer between `UI` and the `data repository`. `data repo` is the SSOT of the entire app.

I let the `data repo` handles all business logic (deadline was tight :<). `View models` receive data and send events to `data repo`.

Followed the [UDF pattern](https://developer.android.com/topic/architecture#unidirectional-data-flow), where the data flows in only one direction (from `data repo` to `view models`), and the events that modify the data flow in the opposite direction (`view models` to `data repo`).

When navigating within the navigation graph, data is sent via [route arguments](https://developer.android.com/jetpack/compose/navigation#nav-with-args).

### Screenshots
