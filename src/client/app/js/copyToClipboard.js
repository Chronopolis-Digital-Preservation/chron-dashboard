/** Copy HTML element with styles to clipboard */
function copyToClipboard(containerId) {
  var container = document.getElementById(containerId);
  if (document.selection) { 
    var range = document.body.createTextRange();
    range.moveToElementText(container);
    range.select().createTextRange();
    document.execCommand("copy"); 
  } else if (window.getSelection) {
    var range = document.createRange();
    try {
      range.selectNodeContents(objTgt);
    } catch (eCapture) {
      range.selectNode(container);
    }

    var selection = window.getSelection();
    selection.removeAllRanges();
    selection.addRange(range);
    document.execCommand("copy");
  }
}