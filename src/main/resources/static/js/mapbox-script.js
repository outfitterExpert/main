"use strict";

let listingsArray = [];

mapboxgl.accessToken = MAPBOX_ACCESS_TOKEN;
var map = new mapboxgl.Map({
    container: 'map', // container ID
    style: 'mapbox://styles/mapbox/streets-v11', // style URL
    center: [-97.109, 32.738], // starting position [lng, lat]
    zoom: 10 // starting zoom
});

map.addControl(new mapboxgl.NavigationControl());


$("#btn").click(function(){
    var userInput = $("#input").val();
    geocode(userInput, MAPBOX_ACCESS_TOKEN).then(function(info){

        console.log(info)
        var newCenter = {
            lng: info[0],
            lat: info[1]
        };

        var marker = new mapboxgl.Marker({
            draggable: true,
            color: "yellow",
        })
            .setLngLat(newCenter)
            .addTo(map)

        var popup = new mapboxgl.Popup()
            .setLngLat(marker.getLngLat())
            .setMaxWidth("200px")
            .addTo(map);

        popup.setHTML("<h3>" + userInput + "</h3>");
        marker.setPopup(popup);
        map.flyTo({center: newCenter});
    });
});

var favRestaurants = [
    //This is how we will format our listing markers
    // {lngLat: [-97.10399646025414, 32.735453629457915], name: "Twisted Root", color: "green", desc: "Best Burger stop in DFW", img: "<img src=img/twistedRoot.jpeg>"},
    // {lngLat: [-97.3435765332706, 32.731558933402624], name: "Wabi House", color: "red", desc: "Best Ramen shop in DFW", img: "<img src=img/wabiHouse.jpeg>"},
    // {lngLat: [-97.11563934491117, 32.69188796621224], name: "Sushi Domo", color: "blue", desc: "Best sushi joint in DFW", img: "<img src=img/sushiDomo.jpeg>"}
]

function createMarkers(databaseString){
    let unpackDatabaseString = databaseString.split("|");
    let listingArray = [];
    for (let i = 0; i < unpackDatabaseString.length; i++){
        listingArray.push(unpackDatabaseString[i].split(","));
    }
    console.log(listingArray);
    return listingArray;
}

$(document).ready(function (){
    let listingsString = $(".listings-info");
    for(let i of listingsString){
        // console.log(i.value);
        listingsArray = createMarkers(i.value);
        //this is pushing array
    }

})

function displayRestaurants (restaurant){
    var markerMaker  = new mapboxgl.Marker({
        draggable: false,
        color: restaurant.color,
    })
        .setLngLat(restaurant.lngLat)
        .addTo(map);

    var markerPopup = new mapboxgl.Popup()
        .setLngLat(restaurant.lngLat)
        .setHTML( restaurant.img + "<h3>" + restaurant.name + "</h3><p>" + restaurant.desc + "</p>")
        .setMaxWidth("200px")
    markerMaker.setPopup(markerPopup);

}
favRestaurants.forEach(displayRestaurants);


//zoom by multiples of 5
$("#change-zoom").click(function(){
    var zoomLevel = $("#zoom-level").val();
    map.setZoom(zoomLevel);
})