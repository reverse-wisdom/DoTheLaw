function saveAuthToCookie(value) {
  document.cookie = `til_auth=${value}`;
}

function saveEmailToCookie(value) {
  document.cookie = `til_email=${value}`;
}
function savePwdToCookie(value) {
  document.cookie = `til_pwd=${value}`;
}

function getAuthFromCookie() {
  return document.cookie.replace(/(?:(?:^|.*;\s*)til_auth\s*=\s*([^;]*).*$)|^.*$/, '$1');
}

function getEmailFromCookie() {
  return document.cookie.replace(/(?:(?:^|.*;\s*)til_email\s*=\s*([^;]*).*$)|^.*$/, '$1');
}
function getPwdFromCookie() {
  return document.cookie.replace(/(?:(?:^|.*;\s*)til_pwd\s*=\s*([^;]*).*$)|^.*$/, '$1');
}

function deleteCookie(value) {
  document.cookie = `${value}=; expires=Thu, 01 Jan 1970 00:00:01 GMT;`;
}

export { saveAuthToCookie, saveEmailToCookie, savePwdToCookie, getAuthFromCookie, getEmailFromCookie, getPwdFromCookie, deleteCookie };
