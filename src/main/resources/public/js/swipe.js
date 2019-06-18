export function swipe() {

    var tasks = document.querySelectorAll(".fa-sign-in-alt");

    var mouseOrigin;
    var isSwiping = false;
    var currentTask;
    var swipeMargin=20;

    Array.prototype.forEach.call(tasks, function addSwipe(element){
        element.addEventListener('touchstart', startSwipe);
    });

    //STARTSWIPE
    function startSwipe(evt){
        evt.target.addEventListener('touchend', endSwipe);
        evt.target.addEventListener('touchmove', detectMouse);

        mouseOrigin = evt.changedTouches[0].pageX;
        currentTask = evt.target;
        isSwiping = true;
    }

    //ENDSWIPE
    function endSwipe(evt){
        evt.target.removeEventListener('touchend', endSwipe);
        evt.target.removeEventListener('mousemove', detectMouse);

        if( currentTask.classList.contains("deleting") ){
            for (let i=0; i<3000; i++) {
                sleep(70).then( () => {
                currentTask.style.marginLeft = (parseFloat(currentTask.style.marginLeft.substring(0, currentTask.style.marginLeft.length -2)) + 0.1) + "px";
                })
            }
            sleep(5000).then( () => {
                currentTask.remove();
            })
        }

        sleep(5000).then( () => {
            mouseOrigin = null;
            isSwiping = false;
            currentTask.style.margin = 0;
            currentTask = null;
        });
    }

    //DETECTMOUSE
    function detectMouse(evt){
        var currentMousePosition = evt.changedTouches[0].pageX;
        var swipeDifference = Math.abs(mouseOrigin - currentMousePosition);

        if(isSwiping && currentTask && (swipeDifference > swipeMargin) ){
            if( (swipeDifference-swipeMargin) <= swipeMargin ){
                //no change, allows user to take no action
                currentTask.classList.remove("deleting");
                currentTask.style.margin = 0;
            }
            else if( mouseOrigin < currentMousePosition ){
                //swipe left
                currentTask.classList.add("deleting");
                currentTask.style.marginLeft = swipeDifference+"px";
            }
        }
    }

};

const sleep = (milliseconds) => {
    return new Promise(resolve => setTimeout(resolve, milliseconds))
}