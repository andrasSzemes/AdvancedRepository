export function swipe(selector) {

    var elements = document.querySelectorAll(selector);

    var mouseOrigin;
    var isSwiping = false;
    var currentTask;
    var swipeMargin = 20;

    Array.prototype.forEach.call(elements, function addSwipe(element) {
        element.addEventListener('touchstart', startSwipe);
    });

    //STARTSWIPE
    function startSwipe(evt) {
        evt.target.addEventListener('touchend', endSwipe);
        evt.target.addEventListener('touchmove', detectMouse);

        mouseOrigin = evt.changedTouches[0].pageY;
        currentTask = document.querySelector(".product-list");
        isSwiping = true;
    }

    //ENDSWIPE
    function endSwipe(evt) {
        evt.target.removeEventListener('touchend', endSwipe);
        evt.target.removeEventListener('mousemove', detectMouse);

        if (currentTask.classList.contains("deleting")) {
            for (let i = 0; i < 5000; i++) {
                sleep(70).then(() => {
                    currentTask.style.marginTop = (parseFloat(currentTask.style.marginTop.substring(0, currentTask.style.marginTop.length - 2)) + 0.1) + "px";
                })
            }
            addClassThenDelete(".products-title", "fade-out4", 0.4)
            sleep(1000).then(() => {
                //currentTask.remove();
                let element = document.querySelector(".product-list");
                element.parentNode.removeChild(element);
            })
        }

        sleep(5000).then(() => {
            mouseOrigin = null;
            isSwiping = false;
            currentTask.style.margin = 0;
            currentTask = null;
        });
    }

    //DETECTMOUSE
    function detectMouse(evt) {
        var currentMousePosition = evt.changedTouches[0].pageY;
        var swipeDifference = Math.abs(mouseOrigin - currentMousePosition);

        if (isSwiping && currentTask && (swipeDifference > swipeMargin)) {
            if ((swipeDifference - swipeMargin) <= swipeMargin) {
                //no change, allows user to take no action
                currentTask.classList.remove("deleting");
                currentTask.style.margin = 0;
            } else if (mouseOrigin < currentMousePosition) {
                //swipe left
                currentTask.classList.add("deleting");
                currentTask.style.marginTop = swipeDifference + "px";
            }
        }
    }

};

const sleep = (milliseconds) => {
    return new Promise(resolve => setTimeout(resolve, milliseconds))
}

function addClassThenDelete(selector, classType, timeOut) {
    let element = document.querySelector(selector);
    element.classList.add(classType);
    sleep(timeOut * 1000).then(() => element.parentNode.removeChild(element));
}