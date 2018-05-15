/**
 * Created by kleba on 10.05.2018.
 */
var j =2;
function addFields(){

    // Container <div> where dynamic content will be placed
    var container = document.getElementById("Order");
    // Clear previous contents of the container

    while (container.hasChildNodes()) {
        container.removeChild(container.lastChild);
    }
    var hidden = document.createElement("input");
    hidden.type = "hidden";
    hidden.name = "command";
    hidden.value = "addorder";
    container.appendChild(hidden);
    for (i=0;i<j;i++) {
        container.appendChild(document.createTextNode("Detail " + (i+1)));
        // Create an <input> element, set its type and name attributes
        var input = document.createElement("input");
        input.type = "text";
        input.name = "name";
        input.id = "name" + i;
        input.placeholder = "name";

        var reason = document.createElement("input");
        reason.type = "text";
        reason.name = "reason";
        reason.id = "reason" + i;
        reason.placeholder = "reason";
        container.appendChild(input);
        container.appendChild(reason)
        // Append a line break
        container.appendChild(document.createElement("br"));
    }
    j+=1;
    var button = document.createElement("input");
    button.type = "submit";
    button.name = "submit";
    container.appendChild(button);
}