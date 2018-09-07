# Conchify for Android
Conchify is an app created on Android Studio, implementing Google Maps 15.0.1, for the creation of Conchos' routes maps, in Santiago de los Caballeros, Santiago, Dominican Republic.



## Installation
Clone this repository and import into **Android Studio**
```bash
git@github.com:masires/conchify.git
```


## Various Resources
* [ColorSafe](http://colorsafe.co/) and [Accessible Color Matrix](https://toolness.github.io/accessible-color-matrix/): ADA compliant color pickers.
* [tycah.ch](https://www.tydac.ch/color/) and [Binary Hex Converter](https://www.binaryhexconverter.com/hex-to-decimal-converter): RGB/HEX to int color convertors.
* [MapStyle](https://mapstyle.withgoogle.com/), [Snazzy Maps](https://snazzymaps.com/) and [MapStylr](http://www.mapstylr.com/): map style generators.
* [MapSharper](http://mapshaper.org/): geojson simplificator.
* [uMap](http://umap.openstreetmap.fr/en/map/new), [Simple-GeoJson](https://tomscholz.github.io/geojson-editor/), [GeoJson.io](http://geojson.io): GeoJson editors.
* [Icon Generator](https://romannurik.github.io/AndroidAssetStudio/icons-launcher.html): icon generator (lol).


## To-Do for v1.0
* Add UI to MainActivity.
* Allow user to change map style manually.
* Static maps with individual maps and related info.
* ~Add UI controllers (compass, toolbar, location, search bar)~.
* ~Refactor GeoJson loading method; create route legend loading method~.
* ~GeoJson layer legend (show route when clicking on layer, or show textbox with available routes)~.
* ~Make sure all GeoJson layer colors are ADA compliant~.
* Add GeoJson template with points of interest (route's offices).
* ~Add "Quejas" form~.
* ~Connect "Quejas" form to MySQL~.


## Maintainers
This project is mantained by:
* [Emanuel Simón](https://github.com/masires)
* [Gabriel Rodríguez](https://github.com/Garoto0518)


## Contributing
1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -m 'Add some feature')
4. Run the linter (ruby lint.rb').
5. Push your branch (git push origin my-new-feature)
6. Create a new Pull Request
