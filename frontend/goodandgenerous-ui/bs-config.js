
/*
 |--------------------------------------------------------------------------
 | Browser-sync config file
 |--------------------------------------------------------------------------
 |
 | For up-to-date information about the options:
 |   http://www.browsersync.io/docs/options/
 |
 | There are more options than you see here, these are just the ones that are
 | set internally. See the website for more info.
 |
 |
 */
 var proxy = require('http-proxy-middleware');

 var apiProxy = proxy('/public', {
     target: 'http://localhost:8080',
     changeOrigin: true   // for vhosted sites
 });

 module.exports = {
     server: {
         middleware: {
             1: apiProxy
         }
     }
 };
