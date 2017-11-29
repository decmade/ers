global.jQuery = require('jquery');
global.$ = jQuery;

require('webpack-jquery-ui');
require('webpack-jquery-ui/css');

import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

require('font-awesome-webpack');
require('datatables');
require('datatables/media/css/jquery.dataTables.min.css');

global.Common = require('./common_vars.js');
require('./common_onload.js');