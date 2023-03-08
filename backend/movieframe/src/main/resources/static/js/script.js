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
        url: 'http://localhost:8081/reviews/{user}?page=1&size='+size,
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

//Create review in server
function newReview(review, callback) {
    $.ajax({
        method: "POST",
        url: 'http://localhost:8081/reviews/new',
        data: JSON.stringify(review),
        processData: false,
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (review) {
        console.log("Review created: " + JSON.stringify(review));
        callback(review);
    })
}

//Delete review from server
function deleteReview(reviewId) {
    $.ajax({
        method: 'DELETE',
        url: 'http://localhost:8081/reviews/' + reviewId
    }).done(function (review) {
        console.log("Deleted review " + reviewId)
    })
}

//Show review in page
function showReview(review) {

    $('.comments').append(
        '<div class="cmt-item flex-it"><div class="author-infor"><div class="flex-it2"><h6>' + review.user + '</h6></div><p>' + review.title + 
        '</p><p>' + review.coments + '</p><p><a class="rep-btn" href="/reviews/{{id}}/delete">+ Delete</a></p></div></div>'
    )
}

$(document).ready(function () {

    loadMovies(function (movies) {
        const ctx = document.getElementById('myChart');

        //charge just 4 films from server
        titles = []
        for(var i=0; i<movies.length; i++){
            titles.append(movies[i].getTitle());
        }

        numReviews = []
        for(var i=0; i<movies.length; i++){
            numReviews.append(movies[i].getReviews().size());
        }
    
        new Chart(ctx, {
        type: 'bar',
        data: {
            labels: titles,
            datasets: [{
            label: 'Graph',
            data: numReviews,
            borderWidth: 1
            }]
        },
        options: {
            scales: {
            y: {
                beginAtZero: true
            }
            }
        }
        });
    });

    var input_radio = $('#vale_radio')
    var input_description = $('#value_description')
    var input_author = $('#value_author')
    var input_title = $('#value_title')

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

    //Handle write_review button
    $("#write_review").click(function () {

        var val_radio = input_radio.val();
        input_radio.val('');
        var val_description = input_description.val();
        input_description.val('');

        var review = {
            rating:val_radio,
            description: val_description,
            user:input_author,
            title:input_title
        }

        newReview(review, function (reviewWithId) {
            //When item with id is returned from server
            showReview(reviewWithId);
        });
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
