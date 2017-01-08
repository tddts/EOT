function trader_login(){
  // click authorize button
  document.getElementsByClassName('authorize__btn')[0].click();

  // move popup window to top left corner
  var popup = document.getElementsByClassName('api-popup-dialog-wrapper')[0];
  popup.style.top = '0px';
  popup.style.left = '0px';

  // check all checkboxes
  var checkBoxes = document.querySelectorAll('[data-scope]');
  for (i = 0; i < checkBoxes.length; i++) {
    checkBoxes[i].click();
  }
};

trader_login();