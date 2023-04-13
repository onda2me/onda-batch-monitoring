function toggleText(id, str1, str2) {
  var x = document.getElementById(id);
  if (x.innerHTML === str1) {
    x.innerHTML = str2;
  } else {
    x.innerHTML = str1;
  }
}

function toggleLike(x) {
  x.classList.toggle("fa-thumbs-up");
}

function toggleHideShow(id) {
  var x = document.getElementById(id);
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}

function toggleDarkMode() {
   var element = document.body;
   element.classList.toggle("dark-mode");
}

function wideNav() {
  document.getElementById("sideContainer").style.width = "300px";
  document.getElementById("main").style.marginLeft = "302px";
  document.getElementById("wideNav").style.display = "none";
  document.getElementById("narrowNav").style.display = "block";
}

function narrowNav() {
  document.getElementById("sideContainer").style.width = "250px";
  document.getElementById("main").style.marginLeft = "252px";
  document.getElementById("wideNav").style.display = "block";
  document.getElementById("narrowNav").style.display = "none";
}

function goHref(url) {
  document.location.href = url;
}
function goAction(act) {
  frm.action = act;
  frm.submit();
}

function goSubmit() {     
  frm.submit();
}