// Set up cookie and decide what action should be when the document is ready
$( document ).ready(function() {
    // Check for cookie, if none, set one up
    var cookieValue = $.cookie("peekadate")
    if (typeof cookieValue == "undefined") {
        $.cookie("peekadate", 5, { expires : 1 });
    }

    var nUnblurLeft = $.cookie("peekadate");
    setUnblurString( nUnblurLeft );

    console.log( "ready!" );
});

// Decrement unblur counter on click on unblur button
$( document ).on( 'click', '.unblur-button', function () {
    var nUnblurLeft = parseInt($.cookie("peekadate"));

    if ( nUnblurLeft > 0 ) {
        var statusUnblur = isPhotoUnblurred();
        if ( statusUnblur == 0 ) {
            // Unblur photo
            unblurPicture()

            // Decrease unblur counter by 1
            nUnblurLeft -= 1;
            $.cookie("peekadate", nUnblurLeft);

            // Set new unblur string
            setUnblurString( nUnblurLeft );
        }
    }
    else {
        $( '#light' ).css( "display", 'block' );
        $( '#fade' ).css( "display", 'block' );
    }
});

// Updates the unblur string
function setUnblurString( nUnblur ) {
    var newUnblurStr = "";
    if ( nUnblur <= 1 ) {
        newUnblurStr = "You have " + nUnblur.toString() + " unblur left.";
    }
    else {
        newUnblurStr = "You have " + nUnblur.toString() + " unblurs left.";
    }

    $( ".status" ).html(newUnblurStr);
};

// Unblurs the picture
function unblurPicture() {
    var blurredPictureName = $( ".profile-pic" ).attr("src");
    var blurPos = blurredPictureName.indexOf('-blur');
    var unblurredPictureName = blurredPictureName.substr(1, blurPos - 1) + "-clear.png";

    $( ".profile-pic" ).attr("src", unblurredPictureName);
}

// Checks if photo is unblurred
function isPhotoUnblurred() {
    var blurredPictureName = $( ".profile-pic" ).attr("src");
    if ( blurredPictureName.indexOf('blur') > 0 ){
        return 0;
    }
    else {
        return 1;
    }
}