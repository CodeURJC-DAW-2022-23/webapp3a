function loadReviews(callback, size){
    $.ajax({
        url: 'http://localhost:8081/reviews/?page=1&size='+size,
    })
    .then(response => {
        if(response.status !== 200){
            console.log("Looks like there was a problem. Status Code: " + response.status);
            return;
        }
        response.ajax().done(data => {//function(callback) that is executed once the response is received
            //console.log("Response received");
            sleep(2000);
            $('.pagination').html("")
            console.log("Data:",JSON.stringify(data));
            callback(data);
        }).catch(function(err){
            console.log("AJAX parse Error :-S",err);
        });
    })
    .catch(function(err){
        console.log("Error :-S",err);
    });
}

function loadUserReviews(callback, size){
    $.ajax({
        url: 'http://localhost:8081/{user}?page=1&size='+size,
    })
    .then(response => {
        if(response.status !== 200){
            console.log("Looks like there was a problem. Status Code: " + response.status);
            return;
        }
        response.ajax().done(data => {//function(callback) that is executed once the response is received
            //console.log("Response received");
            sleep(2000);
            $('.pagination').html("")
            console.log("Data:",JSON.stringify(data));
            callback(data);
        }).catch(function(err){
            console.log("AJAX parse Error :-S",err);
        });
    })
    .catch(function(err){
        console.log("Error :-S",err);
    });
}

//Show review in page
function showReview(review) {

    $('.comments').append(
        '<div class="cmt-item flex-it"><div class="author-infor"><div class="flex-it2"><h6>' + review.user + '</h6></div><p>' + review.title + 
        '</p><p>' + review.coments + '</p><p><a class="rep-btn" href="/reviews/{{id}}/delete">+ Delete</a></p></div></div>'
    )
}

function loadMovies(callback){
    $.ajax({
        url: 'https://localhost:8081/movies',
    })
    .then(response => {
        if(response.status !== 200){
            console.log("Looks like there was a problem. Status Code: " + response.status);
            return;
        }
        response.ajax().done(data => {//function(callback) that is executed once the response is received
            //console.log("Response received");
            sleep(2000);
            //$('.pagination').html("")
            console.log("Data:",JSON.stringify(data));
            callback(data);
        }).catch(function(err){
            console.log("AJAX parse Error :-S",err);
        });
    })
    .catch(function(err){
        console.log("Error :-S",err);
    });
}

$(document).ready(function () {

    //Handle delete buttons
    $('.comments').click(function (event) {
        var elem = $(event.target);
        if (elem.is('a')) {
            var reviewDiv = elem.parent();
            var reviewId = reviewDiv.attr('div');
            reviewDiv.remove()
            deleteItem(reviewId);
        }
    })

    $("#show_user_reviews").click(function () {

        $('.pagination').html('<div  class="spinner"></div>');
        loadUserReviews(function (reviews) {
            //When reviews are loaded from server
            for (var i = 0; i < reviews.length; i++) {
                showReview(reviews[i]);
            }
        });
    })  
    
    $("#show_reviews").click(function () {

        $('.pagination').html('<div  class="spinner"></div>');
        loadReviews(function (reviews) {
            //When reviews are loaded from server
            for (var i = 0; i < reviews.length; i++) {
                showReview(reviews[i]);
            }
        });
    }) 

    //upload more elements when pressed button "more results"
    $("#more").click(function () {
        $('.pagination').html('<div  class="spinner"></div>');
        loadReviews(function (reviews) {
            //When reviews are loaded from server
            for (var i = 0; i < reviews.length; i++) {
                showReview(reviews[i]);
            }
        });
    })
})
