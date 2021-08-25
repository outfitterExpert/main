"use strict";

let listingsArray = [];
let MAPBOX_ACCESS_TOKEN = $(".mapToken").val();
console.log(MAPBOX_ACCESS_TOKEN);
mapboxgl.accessToken = MAPBOX_ACCESS_TOKEN;
var map = new mapboxgl.Map({
    container: 'map', // container ID
    style: 'mapbox://styles/mapbox/streets-v11', // style URL
    center: [-97.109, 32.738], // starting position [lng, lat]
    zoom: 3 // starting zoom
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
// mapboxgl.accessToken = MAPBOX_ACCESS_TOKEN;
// let id = $(".listing-id");
// let addresses = $(".listing-location");
// let titles = $(".listing-title");
// for(let i = 0; i < addresses.length; i++) {
//     let loc = addresses[i].innerText;
//     let titleText = titles[i].innerText;
//     geocode(loc, MAPBOX_ACCESS_TOKEN).then(function (address) {
//         let popup = new mapboxgl.Popup(popupOptions)
//             .setHTML(
//                 '<p style="font-size: 15px; font-weight: bold;">' + titleText + '</p>' +
//                 '<p>' + loc + '</p>'
//             );
//         let marker = new mapboxgl.Marker({
//             color: '#007c6f',
//         })
//             .setLngLat(address)
//             .setPopup(popup)
//             .addTo(map);
//     })

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
var popupOptions = {
    anchor: "top",
}
favRestaurants.forEach(displayRestaurants);

// let ids = $(".listing-id"); // not needed
let addresses = $(".listing-location");
let titles = $(".listing-title");
for(let i = 0; i < addresses.length; i++) {
    let loc = addresses[i].innerText;
    let titleText = titles[i].innerText;
    geocode(loc, MAPBOX_ACCESS_TOKEN).then(function (address) {
        let popup = new mapboxgl.Popup(popupOptions)
            .setHTML(
                '<p style="font-size: 15px; font-weight: bold;">' + titleText + '</p>' +
                '<p>' + loc + '</p>'
            );
        let marker = new mapboxgl.Marker({
            color: '#808080',
        })
            .setLngLat(address)
            .setPopup(popup)
            .addTo(map);
    })

//zoom by multiples of 5
    $("#change-zoom").click(function () {
        var zoomLevel = $("#zoom-level").val();
        map.setZoom(zoomLevel);
    })
}
