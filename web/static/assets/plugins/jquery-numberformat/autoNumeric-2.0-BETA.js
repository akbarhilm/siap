/**
* autoNumeric.js
* @author: Bob Knothe
* @contributor: Sokolov Yura
* @version: 2.0-beta - 2014-10-07 GMT 9:00 PM
*
* Created by Robert J. Knothe on 2009-08-09. Please report any bugs to https://github.com/BobKnothe/autoNumeric
*
* Copyright (c) 2009 Robert J. Knothe http://www.decorplanit.com/plugin/
*
* The MIT License (http://www.opensource.org/licenses/mit-license.php)
*
* Permission is hereby granted, free of charge, to any person
* obtaining a copy of this software and associated documentation
* files (the "Software"), to deal in the Software without
* restriction, including without limitation the rights to use,
* copy, modify, merge, publish, distribute, sub license, and/or sell
* copies of the Software, and to permit persons to whom the
* Software is furnished to do so, subject to the following
* conditions:
*
* The above copyright notice and this permission notice shall be
* included in all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
* OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
* HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
* WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
* FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
* OTHER DEALINGS IN THE SOFTWARE.
*/
(function ($) {
    "use strict";
    /*jslint browser: true*/
    /*global jQuery: false, sessionStorage: false*/
    /**
    * Cross browser routine for getting selected range/cursor position
    */
    function getElementSelection(that) {
        var position = {};
        if (that.selectionStart === undefined) {
            that.focus();
            var select = document.selection.createRange();
            position.length = select.text.length;
            select.moveStart('character', -that.value.length);
            position.end = select.text.length;
            position.start = position.end - position.length;
        } else {
            position.start = that.selectionStart;
            position.end = that.selectionEnd;
            position.length = position.end - position.start;
        }
        return position;
    }
    /**
     * Cross browser routine for setting selected range/cursor position
     */
    function setElementSelection(that, start, end) {
        if (that.selectionStart === undefined) {
            that.focus();
            var r = that.createTextRange();
            r.collapse(true);
            r.moveEnd('character', end);
            r.moveStart('character', start);
            r.select();
        } else {
            that.selectionStart = start;
            that.selectionEnd = end;
        }
    }
    /**
     * run callbacks in parameters if any
     * any parameter could be a callback:
     * - a function, which invoked with jQuery element, parameters and this parameter name and returns parameter value
     * - a name of function, attached to $(selector).autoNumeric.functionName(){} - which was called previously
     */
    function runCallbacks($this, settings) {
        /**
         * loops through the settings object (option array) to find the following
         * k = option name example k=aNum
         * val = option value example val=0123456789
         */
        $.each(settings, function (k, val) {
            if (typeof val === 'function') {
                settings[k] = val($this, settings, k);
            } else if (typeof $this.autoNumeric[val] === 'function') {
                /**
                 * calls the attached function from the html5 data example: data-a-sign="functionName"
                 */
                settings[k] = $this.autoNumeric[val]($this, settings, k);
            }
        });
    }
    function convertKeyToNumber(settings, key) {
        if (typeof (settings[key]) === 'string') {
            settings[key] *= 1;
        }
    }
    /**
     * Preparing user defined options for further usage
     * merge them with defaults appropriately
     */
    function autoCode($this, settings) {
        runCallbacks($this, settings);
        settings.oEvent = null;
        settings.tagList = ['b', 'caption', 'cite', 'code', 'dd', 'del', 'div', 'dfn', 'dt', 'em', 'h1', 'h2', 'h3', 'h4', 'h5', 'h6', 'ins', 'kdb', 'label', 'li', 'output', 'p', 'q', 's', 'sample', 'span', 'strong', 'td', 'th', 'u', 'var'];
        var vmax = settings.vMax.toString().split('.'),
            vmin = (!settings.vMin && settings.vMin !== 0) ? [] : settings.vMin.toString().split('.');
        convertKeyToNumber(settings, 'vMax');
        convertKeyToNumber(settings, 'vMin');
        settings.mDec = (settings.mRound === 'CHF') ? '2' : settings.mDec;
        convertKeyToNumber(settings, 'mDec'); /** set mDec if not defined by user */
        settings.allowLeading = true;
        if (settings.oRide !== null) {
            var parts = settings.oRide.split(',');
            settings.aNeg = parts[0] < 0 ? '-' : '';
            settings.mDec = +parts[1];
        } else {
            settings.aNeg = settings.vMin < 0 ? '-' : '';
        }
        vmax[0] = vmax[0].replace('-', '');
        vmin[0] = vmin[0].replace('-', '');
        settings.mInt = Math.max(vmax[0].length, vmin[0].length, 1);
        if (settings.mDec === null) {
            var vmaxLength = 0,
                vminLength = 0;
            if (vmax[1]) {
                vmaxLength = vmax[1].length;
            }
            if (vmin[1]) {
                vminLength = vmin[1].length;
            }
            settings.mDec = Math.max(vmaxLength, vminLength);
            settings.oDec = settings.mDec;
        } /** set alternative decimal separator key */
        if (settings.altDec === null && settings.mDec > 0) {
            if (settings.aDec === '.' && settings.aSep !== ',') {
                settings.altDec = ',';
            } else if (settings.aDec === ',' && settings.aSep !== '.') {
                settings.altDec = '.';
            }
        }
        /** cache regexps for autoStrip */
        var aNegReg = settings.aNeg ? '([-\\' + settings.aNeg + ']?)' : '(-?)';
        settings.aNegRegAutoStrip = aNegReg;
        settings.skipFirstAutoStrip = new RegExp(aNegReg + '[^-' + (settings.aNeg ? '\\' + settings.aNeg : '') + '\\' + settings.aDec + '\\d]' + '.*?(\\d|\\' + settings.aDec + '\\d)');
        settings.skipLastAutoStrip = new RegExp('(\\d\\' + settings.aDec + '?)[^\\' + settings.aDec + '\\d]\\D*$');
        var allowed = '-' + settings.aNum + '\\' + settings.aDec;
        settings.allowedAutoStrip = new RegExp('[^' + allowed + ']', 'gi');
        settings.numRegAutoStrip = new RegExp(aNegReg + '(?:\\' + settings.aDec + '?(\\d+\\' + settings.aDec + '\\d+)|(\\d*(?:\\' + settings.aDec + '\\d*)?))');
        return settings;
    }
    /**
     * places or removes brackets on negative values
     */
    function negativeBracket(s, nBracket, oEvent, suffix, pNeg) { /** oEvent = settings.oEvent */
        if (pNeg === 'right') {
            return s;
        }
        nBracket = nBracket.split(',');
        if (oEvent === 'set' || oEvent === 'focusout') {
            s = s.replace('-', '');
            s = (suffix !== '') ? s.replace(suffix, '') : s;
            s = nBracket[0] + s + nBracket[1] + suffix;
        } else if ((oEvent === 'get' || oEvent === 'focusin' || oEvent === 'pageLoad') && s.charAt(0) === nBracket[0]) {
            s = s.replace(nBracket[0], '-');
            s = s.replace(nBracket[1], '');
        }
        return s;
    }
    /**
     * strip all unwanted characters and leave only a number alert
     */
    function autoStrip(s, settings, strip_zero) {
        if (settings.nBracket !== null && s.indexOf(settings.nBracket.split(',')[0]) !== -1) {
            s = negativeBracket(s, settings.nBracket, 'get', settings.aSuffix, settings.pNeg);
        }
        if (settings.aSign) { /** remove currency sign */
            while (s.indexOf(settings.aSign) > -1) {
                s = s.replace(settings.aSign, '');
            }
        }
        if (settings.aSuffix) { /** remove suffix */
            while (s.indexOf(settings.aSuffix) > -1) {
                s = s.replace(settings.aSuffix, '');
            }
        }
        s = s.replace(settings.skipFirstAutoStrip, '$1$2'); /** first replace anything before digits */
        s = s.replace(settings.skipLastAutoStrip, '$1'); /** then replace anything after digits */
        s = s.replace(settings.allowedAutoStrip, ''); /** then remove any uninterested characters */
        if (settings.altDec) {
            s = s.replace(settings.altDec, settings.aDec);
        } /** get only number string */
        var m = s.match(settings.numRegAutoStrip);
        s = m ? [m[1], m[2], m[3]].join('') : '';
        if ((settings.lZero === 'allow' || settings.lZero === 'keep') && strip_zero !== 'strip') {
            var parts = [],
                nSign = '';
            parts = s.split(settings.aDec);
            if (parts[0].indexOf('-') !== -1) {
                nSign = '-';
                parts[0] = parts[0].replace('-', '');
            }
            if (parts[0].length > settings.mInt && parts[0].charAt(0) === '0') { /** strip leading zero if need */
                parts[0] = parts[0].slice(1);
            }
            s = nSign + parts.join(settings.aDec);
        }
        if ((strip_zero && settings.lZero === 'deny') || (strip_zero && settings.lZero === 'allow' && settings.allowLeading === false)) {
            var strip_reg = '^' + settings.aNegRegAutoStrip + '0*(\\d' + (strip_zero === 'leading' ? ')' : '|$)');
            strip_reg = new RegExp(strip_reg);
            s = s.replace(strip_reg, '$1$2');
        }
        return s;
    }
    /**
     * truncate decimal part of a number
     */
    function truncateDecimal(s, aDec, mDec) {
        if (aDec && mDec) {
            var parts = s.split(aDec);
            /** truncate decimal part to satisfying length
             * cause we would round it anyway */
            if (parts[1] && parts[1].length > mDec) {
                if (mDec > 0) {
                    parts[1] = parts[1].substring(0, mDec);
                    s = parts.join(aDec);
                } else {
                    s = parts[0];
                }
            }
        }
        return s;
    }
    /**
     * prepare number string to be converted to real number
     */
    function fixNumber(s, aDec, aNeg) {
        if (aDec && aDec !== '.') {
            s = s.replace(aDec, '.');
        }
        if (aNeg && aNeg !== '-') {
            s = s.replace(aNeg, '-');
        }
        if (!s.match(/\d/)) {
            s += '0';
        }
        return s;
    }
    /**
     * function to handle numbers less than 0 that are stored in Exponential notation ex: .0000001 stored as 1e-7
     */
    function checkValue(value, settings) {
        if (value) {
            var checkSmall = +value;
            if (checkSmall < 0.000001 && checkSmall > -1) {
                value = +value;
                if (value < 0.000001 && value > 0) {
                    value = (value + 10).toString();
                    value = value.substring(1);
                }
                if (value < 0 && value > -1) {
                    value = (value - 10).toString();
                    value = '-' + value.substring(2);
                }
                value = value.toString();
            } else {
                var parts = value.split('.');
                if (parts[1] !== undefined) {
                    if (+parts[1] === 0) {
                        value = parts[0];
                    } else {
                        parts[1] = parts[1].replace(/0*$/, '');
                        value = parts.join('.');
                    }
                }
            }
        }
        return (settings.lZero === 'keep') ? value : value.replace(/^0*(\d)/, '$1');
    }
    /**
     * prepare real number to be converted to our format
     */
    function presentNumber(s, aDec, aNeg) {
        if (aNeg && aNeg !== '-') {
            s = s.replace('-', aNeg);
        }
        if (aDec && aDec !== '.') {
            s = s.replace('.', aDec);
        }
        return s;
    }
    /**
     * checking that number satisfy format conditions
     * and lays between settings.vMin and settings.vMax
     * and the string length does not exceed the digits in settings.vMin and settings.vMax
     */
    function autoCheck(s, settings) {
        s = s.replace(',', '.');
        s = truncateDecimal(s, settings);
        s = fixNumber(s, settings.aDec, settings.aNeg);
        if (settings.oRide !== null) {
            var parts = settings.oRide.split(','),
                strLength = (s.indexOf('.') === -1) ? s.length : s.indexOf('.');
            if (s.indexOf('-') === -1) {
                if (Math.abs(parts[0]) === '0' && s === '0') {
                    return true;
                }
                return strLength > Math.abs(parts[0]) ? false : true;
            } else {
                return strLength - 1 > Math.abs(parts[0]) ? false : true;
            }
        } else {
            var value = +s;
            return value >= settings.vMin && value <= settings.vMax;
        }
    }
    /**
     * private function to check for empty value
     */
    function checkEmpty(iv, settings, signOnEmpty) {
        if (iv === '' || iv === settings.aNeg) {
            if (settings.wEmpty === 'zero') {
                return iv + '0';
            }
            if (settings.wEmpty === 'sign' || signOnEmpty) {
                return (settings.pNeg === 'left') ? iv + settings.aSign + settings.aSuffix : settings.aSign + iv + settings.aSuffix;
            }
            return iv;
        }
        return null;
    }
    /**
     * private function that formats our number
     */
    function autoGroup(iv, settings) {
        iv = autoStrip(iv, settings);
        var testNeg = iv.replace(',', '.'),
            empty = checkEmpty(iv, settings, true),
            has_aNeg = iv.indexOf(settings.aNeg) !== -1;
        if (empty !== null) {
            return empty;
        }
        var digitalGroup = '';
        if (settings.dGroup === 2) {
            digitalGroup = /(\d)((\d)(\d{2}?)+)$/;
        } else if (settings.dGroup === '2s') {
            digitalGroup = /(\d)((?:\d{2}){0,2}\d{3}(?:(?:\d{2}){2}\d{3})*?)$/;
        } else if (settings.dGroup === 4) {
            digitalGroup = /(\d)((\d{4}?)+)$/;
        } else {
            digitalGroup = /(\d)((\d{3}?)+)$/;
        } /** splits the string at the decimal string */
        var ivSplit = iv.split(settings.aDec);
        if (settings.altDec && ivSplit.length === 1) {
            ivSplit = iv.split(settings.altDec);
        } /** assigns the whole number to the a variable (s) */
        var s = ivSplit[0];
        if (settings.aSep !== '') {
            while (digitalGroup.test(s)) { /** re-inserts the thousand separator via a regular expression */
                s = s.replace(digitalGroup, '$1' + settings.aSep + '$2');
            }
        }
        if (settings.mDec !== 0 && ivSplit.length > 1) {
            if (ivSplit[1].length > settings.mDec) {
                ivSplit[1] = ivSplit[1].substring(0, settings.mDec);
            } /** joins the whole number with the decimal value */
            iv = s + settings.aDec + ivSplit[1];
        } else { /** if whole numbers only */
            iv = s;
        }
        if (settings.aSign) {
            iv = iv.replace(settings.aNeg, '');
            if (settings.pSign === 'p') {
                if (has_aNeg && settings.pNeg === 'right') {
                    iv = settings.aSign + settings.aNeg + iv;
                }
                if (has_aNeg && settings.pNeg === 'left') {
                    iv = settings.aNeg + settings.aSign + iv;
                }
                if (!has_aNeg) {
                    iv = settings.aSign + iv;
                }
            } else {
                if (has_aNeg) {
                    iv = settings.aNeg + iv + settings.aSign;
                } else {
                    iv = iv + settings.aSign;
                }
            }
        }
        if (settings.aSuffix) {
            iv = iv + settings.aSuffix;
        }
        if (settings.oEvent === 'set' && testNeg < 0 && settings.nBracket !== null) { /** removes the negative sign and places brackets */
            iv = negativeBracket(iv, settings.nBracket, settings.oEvent, settings.aSuffix, settings.pNeg);
        }
        return iv;
    }
    /**
     * round number after setting by pasting or $().autoNumericSet()
     * private function for round the number
     * please note this handled as text - JavaScript math function can return inaccurate values
     * also this offers multiple rounding methods that are not easily accomplished in JavaScript
     */
    function autoRound(iv, settings) { /** value to string */
        iv = (iv === '') ? '0' : iv.toString();
        convertKeyToNumber(settings, 'mDec'); /** set mDec to number needed when mDec set by 'update method */
        if (settings.mRound === 'CHF') {
            iv = (Math.round(iv * 20) / 20).toString();
        }
        var ivRounded = '',
            i = 0,
            nSign = '',
            rDec = (typeof (settings.aPad) === 'boolean' || settings.aPad === null) ? (settings.aPad ? settings.mDec : 0) : +settings.aPad;
        var truncateZeros = function (ivRounded) { /** truncate not needed zeros */
            var regex = rDec === 0 ? (/(\.(?:\d*[1-9])?)0*$/) : rDec === 1 ? (/(\.\d(?:\d*[1-9])?)0*$/) : new RegExp('(\\.\\d{' + rDec + '}(?:\\d*[1-9])?)0*');
            ivRounded = ivRounded.replace(regex, '$1'); /** If there are no decimal places, we don't need a decimal point at the end */
            if (rDec === 0) {
                ivRounded = ivRounded.replace(/\.$/, '');
            }
            return ivRounded;
        };
        if (iv.charAt(0) === '-') { /** Checks if the iv (input Value)is a negative value */
            nSign = '-';
            iv = iv.replace('-', ''); /** removes the negative sign will be added back later if required */
        }
        if (!iv.match(/^\d/)) { /** append a zero if first character is not a digit (then it is likely to be a dot)*/
            iv = '0' + iv;
        }
        if (nSign === '-' && +iv === 0) { /** determines if the value is zero - if zero no negative sign */
            nSign = '';
        }
        if ((+iv > 0 && settings.lZero !== 'keep') || (iv.length > 0 && settings.lZero === 'allow')) { /** trims leading zero's if needed */
            iv = iv.replace(/^0*(\d)/, '$1');
        }
        var dPos = iv.lastIndexOf('.'), /** virtual decimal position */
            vdPos = (dPos === -1) ? iv.length - 1 : dPos, /** checks decimal places to determine if rounding is required */
            cDec = (iv.length - 1) - vdPos; /** check if no rounding is required */
        if (cDec <= settings.mDec) {
            ivRounded = iv; /** check if we need to pad with zeros */
            if (cDec < rDec) {
                if (dPos === -1) {
                    ivRounded += '.';
                }
                var zeros = '000000';
                while (cDec < rDec) {
                    zeros = zeros.substring(0, rDec - cDec);
                    ivRounded += zeros;
                    cDec += zeros.length;
                }
            } else if (cDec > rDec) {
                ivRounded = truncateZeros(ivRounded);
            } else if (cDec === 0 && rDec === 0) {
                ivRounded = ivRounded.replace(/\.$/, '');
            }
            if (settings.mRound !== 'CHF') {
                return (+ivRounded === 0) ? ivRounded : nSign + ivRounded;
            }
            if (settings.mRound === 'CHF') {
                dPos = ivRounded.lastIndexOf('.');
                iv = ivRounded;
            }
        } /** rounded length of the string after rounding */
        var rLength = dPos + settings.mDec,
            tRound = +iv.charAt(rLength + 1),
            ivArray = iv.substring(0, rLength + 1).split(''),
            odd = (iv.charAt(rLength) === '.') ? (iv.charAt(rLength - 1) % 2) : (iv.charAt(rLength) % 2),
            onePass = true;
        if (odd !== 1) {
            odd = (odd === 0 && (iv.substring(rLength + 2, iv.length) > 0)) ? 1 : 0;
        }
        if ((tRound > 4 && settings.mRound === 'S') || /** Round half up symmetric */
                (tRound > 4 && settings.mRound === 'A' && nSign === '') || /** Round half up asymmetric positive values */
                (tRound > 5 && settings.mRound === 'A' && nSign === '-') || /** Round half up asymmetric negative values */
                (tRound > 5 && settings.mRound === 's') || /** Round half down symmetric */
                (tRound > 5 && settings.mRound === 'a' && nSign === '') || /** Round half down asymmetric positive values */
                (tRound > 4 && settings.mRound === 'a' && nSign === '-') || /** Round half down asymmetric negative values */
                (tRound > 5 && settings.mRound === 'B') || /** Round half even "Banker's Rounding" */
                (tRound === 5 && settings.mRound === 'B' && odd === 1) || /** Round half even "Banker's Rounding" */
                (tRound > 0 && settings.mRound === 'C' && nSign === '') || /** Round to ceiling toward positive infinite */
                (tRound > 0 && settings.mRound === 'F' && nSign === '-') || /** Round to floor toward negative infinite */
                (tRound > 0 && settings.mRound === 'U') ||
                (settings.mRound === 'CHF')) { /** round up away from zero */
            for (i = (ivArray.length - 1); i >= 0; i -= 1) { /** Round up the last digit if required, and continue until no more 9's are found */
                if (ivArray[i] !== '.') {
                    if (settings.mRound === 'CHF' && ivArray[i] <= 2 && onePass) {
                        ivArray[i] = 0;
                        onePass = false;
                        break;
                    }
                    if (settings.mRound === 'CHF' && ivArray[i] <= 7 && onePass) {
                        ivArray[i] = 5;
                        onePass = false;
                        break;
                    }
                    if (settings.mRound === 'CHF' && onePass) {
                        ivArray[i] = 10;
                        onePass = false;
                    } else {
                        ivArray[i] = +ivArray[i] + 1;
                    }
                    if (ivArray[i] < 10) {
                        break;
                    }
                    if (i > 0) {
                        ivArray[i] = '0';
                    }
                }
            }
        }
        ivArray = ivArray.slice(0, rLength + 1); /** Reconstruct the string, converting any 10's to 0's */
        ivRounded = truncateZeros(ivArray.join('')); /** return rounded value */
        return (+ivRounded === 0) ? ivRounded : nSign + ivRounded;
    }
    /**
     * Holder object for field properties
     */
    function AutoNumericHolder(that, settings) {
        this.settings = settings;
        this.that = that;
        this.$that = $(that);
        this.formatted = false;
        this.settingsClone = autoCode(this.$that, this.settings);
        this.value = that.value;
    }
    AutoNumericHolder.prototype = {
        init: function (e) {
            this.value = this.that.value;
            this.settingsClone = autoCode(this.$that, this.settings);
            this.ctrlKey = e.ctrlKey;
            this.cmdKey = e.metaKey;
            this.shiftKey = e.shiftKey;
            this.selection = getElementSelection(this.that); /** keypress event overwrites meaningful value of e.keyCode */
            if (e.type === 'keydown' || e.type === 'keyup') {
                this.kdCode = e.keyCode;
            }
            this.which = e.which;
            this.processed = false;
            this.formatted = false;
        },
        setSelection: function (start, end, setReal) {
            start = Math.max(start, 0);
            end = Math.min(end, this.that.value.length);
            this.selection = {
                start: start,
                end: end,
                length: end - start
            };
            if (setReal === undefined || setReal) {
                setElementSelection(this.that, start, end);
            }
        },
        setPosition: function (pos, setReal) {
            this.setSelection(pos, pos, setReal);
        },
        getBeforeAfter: function () {
            var value = this.value,
                left = value.substring(0, this.selection.start),
                right = value.substring(this.selection.end, value.length);
            return [left, right];
        },
        getBeforeAfterStriped: function () {
            var parts = this.getBeforeAfter();
            parts[0] = autoStrip(parts[0], this.settingsClone);
            parts[1] = autoStrip(parts[1], this.settingsClone);
            return parts;
        },
        /**
         * strip parts from excess characters and leading zeroes
         */
        normalizeParts: function (left, right) {
            var settingsClone = this.settingsClone;
            right = autoStrip(right, settingsClone); /** if right is not empty and first character is not aDec, */
            /** we could strip all zeros, otherwise only leading */
            var strip = right.match(/^\d/) ? true : 'leading';
            left = autoStrip(left, settingsClone, strip); /** prevents multiple leading zeros from being entered */
            if ((left === '' || left === settingsClone.aNeg) && settingsClone.lZero === 'deny') {
                if (right > '') {
                    right = right.replace(/^0*(\d)/, '$1');
                }
            }
            if (settingsClone.oRide !== null) {
                var parts = settingsClone.oRide.split(',');
                if (left.indexOf('-') === -1) {
                    if (left.charAt(0) === '0' && left.length > Math.abs(parts[0])) {
                        left = (left === '0') ? left : left.substr(1);
                    }
                } else {
                    if (left.length - 1 > Math.abs(parts[0])) {
                        left = left.substr(1);
                        left = (left.charAt(0) === '0') ? left.substr(1) : left;
                        left = '-' + left;
                    }
                }
            }
            var new_value = left + right; /** insert zero if has leading dot */
            if (settingsClone.aDec) {
                var m = new_value.match(new RegExp('^' + settingsClone.aNegRegAutoStrip + '\\' + settingsClone.aDec));
                if (m) {
                    left = left.replace(m[1], m[1] + '0');
                    new_value = left + right;
                }
            } /** insert zero if number is empty and settingsClone.wEmpty == 'zero' */
            if (settingsClone.wEmpty === 'zero' && (new_value === settingsClone.aNeg || new_value === '')) {
                left += '0';
            }
            return [left, right];
        },
        /**
         * set part of number to value keeping position of cursor
         */
        setValueParts: function (left, right) {
            var settingsClone = this.settingsClone,
                parts = this.normalizeParts(left, right),
                new_value = parts.join(''),
                position = parts[0].length;
            if (autoCheck(new_value, settingsClone)) {
                new_value = truncateDecimal(new_value, settingsClone);
                var test_value = (new_value.indexOf(',') !== -1) ? new_value.replace(',', '.') : new_value,
                    text_value = test_value;
                if (test_value === '' || test_value === '-') {
                    settingsClone.rawValue = '';
                } else {
                    if (+test_value < 0.000001 && +test_value > -1) {
                        test_value = checkValue(test_value, settingsClone);
                    } else {
                        test_value = test_value.toString();
                    }
                    settingsClone.rawValue =  settingsClone.oRide === null ? test_value : text_value;
                }
                if (position > new_value.length) {
                    position = new_value.length;
                }
                this.value = new_value;
                this.setPosition(position, false);
                return true;
            }
            return false;
        },
        /**
         * helper function for expandSelectionOnSign
         * returns sign position of a formatted value
         */
        signPosition: function () {
            var settingsClone = this.settingsClone,
                aSign = settingsClone.aSign,
                that = this.that;
            if (aSign) {
                var aSignLen = aSign.length;
                if (settingsClone.pSign === 'p') {
                    var hasNeg = settingsClone.aNeg && that.value && that.value.charAt(0) === settingsClone.aNeg;
                    return hasNeg ? [1, aSignLen + 1] : [0, aSignLen];
                }
                var valueLen = that.value.length;
                return [valueLen - aSignLen, valueLen];
            }
            return [1000, -1];
        },
        /**
         * expands selection to cover whole sign
         * prevents partial deletion/copying/overwriting of a sign
         */
        expandSelectionOnSign: function (setReal) {
            var sign_position = this.signPosition(),
                selection = this.selection;
            if (selection.start < sign_position[1] && selection.end > sign_position[0]) { /** if selection catches something except sign and catches only space from sign */
                if ((selection.start < sign_position[0] || selection.end > sign_position[1]) && this.value.substring(Math.max(selection.start, sign_position[0]), Math.min(selection.end, sign_position[1])).match(/^\s*$/)) { /** then select without empty space */
                    if (selection.start < sign_position[0]) {
                        this.setSelection(selection.start, sign_position[0], setReal);
                    } else {
                        this.setSelection(sign_position[1], selection.end, setReal);
                    }
                } else { /** else select with whole sign */
                    this.setSelection(Math.min(selection.start, sign_position[0]), Math.max(selection.end, sign_position[1]), setReal);
                }
            }
        },
        /**
         * try to strip pasted value to digits
         */
        checkPaste: function () {
            if (this.valuePartsBeforePaste !== undefined) {
                var parts = this.getBeforeAfter(),
                    oldParts = this.valuePartsBeforePaste;
                delete this.valuePartsBeforePaste; /** try to strip pasted value first */
                parts[0] = parts[0].substr(0, oldParts[0].length) + autoStrip(parts[0].substr(oldParts[0].length), this.settingsClone);
                if (!this.setValueParts(parts[0], parts[1])) {
                    this.value = oldParts.join('');
                    this.setPosition(oldParts[0].length, false);
                }
            }
        },
        /**
         * process pasting, cursor moving and skipping of not interesting keys
         * if returns true, further processing is not performed
         */
        skipAllways: function (e) {
            var kdCode = this.kdCode,
                which = this.which,
                ctrlKey = this.ctrlKey,
                cmdKey = this.cmdKey,
                shiftKey = this.shiftKey; /** catch the ctrl up on ctrl-v */
            if (((ctrlKey || cmdKey) && e.type === 'keyup' && this.valuePartsBeforePaste !== undefined) || (shiftKey && kdCode === 45)) {
                this.checkPaste();
                return false;
            }
            /** codes are taken from http://www.cambiaresearch.com/c4/702b8cd1-e5b0-42e6-83ac-25f0306e3e25/Javascript-Char-Codes-Key-Codes.aspx
             * skip Fx keys, windows keys, other special keys
             */
            if ((kdCode >= 112 && kdCode <= 123) || (kdCode >= 91 && kdCode <= 93) || (kdCode >= 9 && kdCode <= 31) || (kdCode < 8 && (which === 0 || which === kdCode)) || kdCode === 144 || kdCode === 145 || kdCode === 45) {
                return true;
            }
            if ((ctrlKey || cmdKey) && kdCode === 65) { /** if select all (a=65)*/
                if (this.settings.sNumber){
                    e.preventDefault();
                    var start = this.settings.aSign.length;
                    var end = this.that.value.length - this.settings.aSuffix.length;
                    setElementSelection(this.that, start, end);
                }
                return true;
            }
            if ((ctrlKey || cmdKey) && (kdCode === 67 || kdCode === 86 || kdCode === 88)) { /** if copy (c=67) paste (v=86) or cut (x=88) */
                if (e.type === 'keydown') {
                    this.expandSelectionOnSign();
                }
                if (kdCode === 86 || kdCode === 45) { /** try to prevent wrong paste */
                    if (e.type === 'keydown' || e.type === 'keypress') {
                        if (this.valuePartsBeforePaste === undefined) {
                            this.valuePartsBeforePaste = this.getBeforeAfter();
                        }
                    } else {
                        this.checkPaste();
                    }
                }
                return e.type === 'keydown' || e.type === 'keypress' || kdCode === 67;
            }
            if (ctrlKey || cmdKey) {
                return true;
            }
            if (kdCode === 37 || kdCode === 39) { /** jump over thousand separator */
                var aSep = this.settingsClone.aSep,
                    aDec = this.settingsClone.aDec,
                    start = this.selection.start,
                    value = this.that.value;
                if (e.type === 'keydown' && !this.shiftKey) {
                    if (kdCode === 37 && (value.charAt(start - 2) === aSep || value.charAt(start - 2) === aDec)) {
                        this.setPosition(start - 1);
                    } else if (kdCode === 39 && (value.charAt(start + 1) === aSep || value.charAt(start + 1) === aDec)) {
                        this.setPosition(start + 1);
                    }
                }
                return true;
            }
            if (kdCode >= 34 && kdCode <= 40) {
                return true;
            }
            return false;
        },
        /**
         * process deletion of characters
         * returns true if processing performed
         */
        processAllways: function () {
            var parts; /** process backspace or delete */
            if (this.kdCode === 8 || this.kdCode === 46) {
                if (!this.selection.length) {
                    parts = this.getBeforeAfterStriped();
                    if (this.kdCode === 8) {
                        parts[0] = parts[0].substring(0, parts[0].length - 1);
                    } else {
                        parts[1] = parts[1].substring(1, parts[1].length);
                    }
                    this.setValueParts(parts[0], parts[1]);
                } else {
                    this.expandSelectionOnSign(false);
                    parts = this.getBeforeAfterStriped();
                    this.setValueParts(parts[0], parts[1]);
                }
                return true;
            }
            return false;
        },
        /**
         * process insertion of characters
         * returns true if processing performed
         */
        processKeypress: function () {
            var settingsClone = this.settingsClone,
                cCode = String.fromCharCode(this.which),
                parts = this.getBeforeAfterStriped(),
                left = parts[0],
                right = parts[1]; /** start rules when the decimal character key is pressed */
            /** always use numeric pad dot to insert decimal separator */
            if (cCode === settingsClone.aDec || (settingsClone.altDec && cCode === settingsClone.altDec) || ((cCode === '.' || cCode === ',') && this.kdCode === 110)) { /** do not allow decimal character if no decimal part allowed */
                if (!settingsClone.mDec || !settingsClone.aDec) {
                    return true;
                } /** do not allow decimal character before aNeg character */
                if (settingsClone.aNeg && right.indexOf(settingsClone.aNeg) > -1) {
                    return true;
                } /** do not allow decimal character if other decimal character present */
                if (left.indexOf(settingsClone.aDec) > -1) {
                    return true;
                }
                if (right.indexOf(settingsClone.aDec) > 0) {
                    return true;
                }
                if (right.indexOf(settingsClone.aDec) === 0) {
                    right = right.substr(1);
                }
                this.setValueParts(left + settingsClone.aDec, right);
                return true;
            } /** start rule on negative sign */

            if (cCode === '-' || cCode === '+') { /** prevent minus if not allowed */
                if (!settingsClone.aNeg) {
                    return true;
                } /** caret is always after minus */
                if (left === '' && right.indexOf(settingsClone.aNeg) > -1) {
                    left = settingsClone.aNeg;
                    right = right.substring(1, right.length);
                } /** change sign of number, remove part if should */
                if (left.charAt(0) === settingsClone.aNeg) {
                    left = left.substring(1, left.length);
                } else {
                    left = (cCode === '-') ? settingsClone.aNeg + left : left;
                }
                this.setValueParts(left, right);
                return true;
            } /** digits */
            if (cCode >= '0' && cCode <= '9') { /** if try to insert digit before minus */
                if (settingsClone.aNeg && left === '' && right.indexOf(settingsClone.aNeg) > -1) {
                    left = settingsClone.aNeg;
                    right = right.substring(1, right.length);
                }
                if (settingsClone.vMax <= 0 && settingsClone.vMin < settingsClone.vMax && this.value.indexOf(settingsClone.aNeg) === -1 && cCode !== '0') {
                    left = settingsClone.aNeg + left;
                }
                this.setValueParts(left + cCode, right);
                return true;
            } /** prevent any other character */
            return true;
        },
        /**
         * formatting of just processed value with keeping of cursor position
         */
        formatQuick: function () {
            var settingsClone = this.settingsClone,
                parts = this.getBeforeAfterStriped(),
                leftLength = this.value;
            if ((settingsClone.aSep === '' || (settingsClone.aSep !== '' && leftLength.indexOf(settingsClone.aSep) === -1)) && (settingsClone.aSign === '' || (settingsClone.aSign !== '' && leftLength.indexOf(settingsClone.aSign) === -1))) {
                var subParts = [],
                    nSign = '';
                subParts = leftLength.split(settingsClone.aDec);
                if (subParts[0].indexOf('-') > -1) {
                    nSign = '-';
                    subParts[0] = subParts[0].replace('-', '');
                    parts[0] = parts[0].replace('-', '');
                }
                if (subParts[0].length > settingsClone.mInt && parts[0].charAt(0) === '0') { /** strip leading zero if need */
                    parts[0] = parts[0].slice(1);
                }
                parts[0] = nSign + parts[0];
            }
            var value = autoGroup(this.value, this.settingsClone),
                position = value.length;
            if (value) {
                /** prepare regexp which searches for cursor position from unformatted left part */
                var left_ar = parts[0].split(''),
                    i = 0;
                for (i; i < left_ar.length; i += 1) { /** thanks Peter Kovari */
                    if (!left_ar[i].match('\\d')) {
                        left_ar[i] = '\\' + left_ar[i];
                    }
                }
                var leftReg = new RegExp('^.*?' + left_ar.join('.*?')),
                    newLeft = value.match(leftReg);/** search cursor position in formatted value */
                if (newLeft) {
                    position = newLeft[0].length;
                    /** if we are just before sign which is in prefix position */
                    if (((position === 0 && value.charAt(0) !== settingsClone.aNeg) || (position === 1 && value.charAt(0) === settingsClone.aNeg)) && settingsClone.aSign && settingsClone.pSign === 'p') {
                        /** place caret after prefix sign */
                        position = this.settingsClone.aSign.length + (value.charAt(0) === '-' ? 1 : 0);
                    }
                } else {
                    if (settingsClone.aSign && settingsClone.pSign === 's') {
                        /** if we could not find a place for cursor and have a sign as a suffix */
                        /** place caret before suffix currency sign */
                        position -= settingsClone.aSign.length;
                    }
                    if (settingsClone.aSuffix) {
                        /** if we could not find a place for cursor and have a suffix */
                        /** place caret before suffix */
                        position -= settingsClone.aSuffix.length;
                    }
                }
            }
            this.that.value = value;
            this.setPosition(position);
            this.formatted = true;
        }
    };
    /** thanks to Anthony & Evan C */
    function autoGet(obj) {
        if (typeof obj === 'string') {
            obj = obj.replace(/\[/g, "\\[").replace(/\]/g, "\\]");
            obj = '#' + obj.replace(/(:|\.)/g, '\\$1');
            /** obj = '#' + obj.replace(/([;&,\.\+\*\~':"\!\^#$%@\[\]\(\)=>\|])/g, '\\$1'); */
            /** possible modification to replace the above 2 lines */
        }
        return $(obj);
    }
    function getHolder($that, settings, update) {
        var data = $that.data('autoNumeric');
        if (!data) {
            data = {};
            $that.data('autoNumeric', data);
        }
        var holder = data.holder;
        if ((holder === undefined && settings) || update) {
            holder = new AutoNumericHolder($that.get(0), settings);
            data.holder = holder;
        }
        return holder;
    }
    /** original settings saved for use when eDec & nSep options are being used */
    function originalSettings(settings) {
        settings.oDec = settings.mDec;
        settings.oPad = settings.aPad;
        settings.oBracket = settings.nBracket;
        settings.oSep = settings.aSep;
        settings.oSign = settings.aSign;
        return settings;
    }
    function readCookie(name) { /** from quirksmode */
        var nameEQ = name + "=",
            ca = document.cookie.split(';'),
            i = 0,
            c = '';
        for (i; i < ca.length; i += 1) {
            c = ca[i];
            while (c.charAt(0) === ' ') {
                c = c.substring(1, c.length);
            }
            if (c.indexOf(nameEQ) === 0) {
                return c.substring(nameEQ.length, c.length);
            }
        }
        return null;
    }
    function storageTest() { /** from moderizr */
        var test = 'test';
        try {
            sessionStorage.setItem(test, test);
            sessionStorage.removeItem(test);
            return true;
        } catch (e) {
            return false;
        }
    }
    function autoSave($this, settings, toDo) {
        if (settings.aStor) {
            var storedName = ($this[0].name !== '' && $this[0].name !== undefined) ? 'AUTO_' + decodeURIComponent($this[0].name) : 'AUTO_' + $this[0].id;
            if (storageTest() === false) { /** sets cookie for browser that do not support sessionStorage IE 6 & ie7 */
                if (toDo === 'get') {
                    return readCookie(storedName);
                }
                if (toDo === 'set') {
                    document.cookie = storedName + '=' + settings.rawValue + '; expires= ; path=/';
                }
                if (toDo === 'wipe') {
                    var date = new Date();
                    date.setTime(date.getTime() + (-1 * 24 * 60 * 60 * 1000));
                    var expires = "; expires=" + date.toGMTString();
                    document.cookie = storedName + '="" ;' + expires + '; path=/';
                }
            } else {
                if (toDo === 'get') {
                    return sessionStorage.getItem(storedName);
                }
                if (toDo === 'set') {
                    sessionStorage.setItem(storedName, settings.rawValue);
                }
                if (toDo === 'wipe') {
                    sessionStorage.removeItem(storedName);
                }
            }
        }
        return;
    }
    var methods = {
        init: function (options) {
            return this.each(function () {
                var $this = $(this),
                    settings = $this.data('autoNumeric'),/** attempt to grab 'autoNumeric' settings, if they don't exist returns "undefined". */
                    tagData = $this.data();/** attempt to grab HTML5 data, if they don't exist we'll get "undefined".*/
                if (typeof settings !== 'object') { /** If we couldn't grab settings, create them from defaults and passed options. */
                    var defaults = {
                        /** allowed numeric values
                         * please do not modify
                         */
                        aNum: '0123456789',
                        /** allowed thousand separator characters
                         * comma = ','
                         * period "full stop" = '.'
                         * apostrophe is escaped = '\''
                         * space = ' '
                         * none = ''
                         * NOTE: do not use numeric characters
                         */
                        aSep: ',',
                        /** when true & the input has focus only the decimal character is visible
                         */
                        nSep: false,
                        /** digital grouping for the thousand separator used in Format
                         * dGroup: '2', results in 99,99,99,999 common in India for values less than 1 billion and greater than -1 billion
                         * dGroup: '3', results in 999,999,999 default
                         * dGroup: '4', results in 9999,9999,9999 used in some Asian countries
                         */
                        dGroup: '3',
                        /** allowed decimal separator characters
                         * period "full stop" = '.'
                         * comma = ','
                         */
                        aDec: '.',
                        /** allow to declare alternative decimal separator which is automatically replaced by aDec
                         * developed for countries the use a comma ',' as the decimal character
                         * and have keyboards\numeric pads that have a period 'full stop' as the decimal characters (Spain is an example)
                         */
                        altDec: null,
                        /** allowed currency symbol
                         * Must be in quotes aSign: '$', a space is allowed aSign: '$ '
                         */
                        aSign: '',
                        /** placement of currency sign
                         * for prefix pSign: 'p',
                         * for suffix pSign: 's',
                         */
                        pSign: 'p',
                        /** placement of negative sign to the right of the currency sign
                         * to the left of the currency sign pNeg: 'left',
                         * to the right of the currency sign pNeg: 'right',
                         */
                        pNeg: 'left',
                        /** Additional suffix
                         * Must be in quotes aSuffix: 'gross', a space is allowed aSuffix: ' gross'
                         */
                        aSuffix: '',
                        /** maximum possible value
                         * value must be enclosed in quotes and use the period for the decimal point
                         * value must be larger than vMin
                         */
                        vMax: '9999999999999.99',
                        /** minimum possible value
                         * value must be enclosed in quotes and use the period for the decimal point
                         * value must be smaller than vMax
                         */
                        vMin: '-9999999999999.99',
                        /** Maximum number of decimal places = used to override decimal places set by the vMin & vMax values
                         * value must be enclosed in quotes example mDec: '3',
                         */
                        mDec: null,
                        /** Expanded decimal places visible when input has focus
                         * value must be enclosed in quotes example mDec: '3',
                         */
                        eDec: null,
                        /** overrides min / max values and controls the number of integers
                         * example oRide: '6,0' allows 6 digits positive 0 negative
                         * example oRide: '8,8' allow 8 digits both positive and negative
                         * decimal places are controlled via the mDec option default is 2 places
                         */
                        oRide: null,
                        /** Set to true to allow the eDec value to be saved with sessionStorage
                         * if ie 6 or 7 the value will be saved as a session cookie
                         */
                        aStor: true,
                        /** method used for rounding
                         * mRound: 'S', Round-Half-Up Symmetric (default)
                         * mRound: 'A', Round-Half-Up Asymmetric
                         * mRound: 's', Round-Half-Down Symmetric (lower case s)
                         * mRound: 'a', Round-Half-Down Asymmetric (lower case a)
                         * mRound: 'B', Round-Half-Even "Bankers Rounding"
                         * mRound: 'U', Round Up "Round-Away-From-Zero"
                         * mRound: 'D', Round Down "Round-Toward-Zero" - same as truncate
                         * mRound: 'C', Round to Ceiling "Toward Positive Infinity"
                         * mRound: 'F', Round to Floor "Toward Negative Infinity"
                         * mRound: 'CHF' Rounds to the nearest .00 .05 - Switzerland
                         */
                        mRound: 'S',
                        /** controls decimal padding
                         * aPad: true - always Pad decimals with zeros
                         * aPad: false - does not pad with zeros.
                         * aPad: `some number` - pad decimals with zero to number different from mDec
                         * thanks to Jonas Johansson for the suggestion
                         */
                        aPad: true,
                        /** places brackets on negative value -$ 999.99 to (999.99)
                         * visible only when the field does NOT have focus the left and right symbols should be enclosed in quotes and separated by a comma
                         * nBracket: null, nBracket: '(,)', nBracket: '[,]', nBracket: '<,>' or nBracket: '{,}'
                         */
                        nBracket: null,
                        /** Displayed on empty string
                         * wEmpty: 'empty', - input can be blank
                         * wEmpty: 'zero', - displays zero
                         * wEmpty: 'sign', - displays the currency sign
                         */
                        wEmpty: 'empty',
                        /** controls leading zero behavior
                         * lZero: 'allow', - allows leading zeros to be entered. Zeros will be truncated when entering additional digits. On focusout zeros will be deleted.
                         * lZero: 'deny', - allows only one leading zero on values less than one
                         * lZero: 'keep', - allows leading zeros to be entered. on fousout zeros will be retained.
                         */
                        lZero: 'allow',
                        /** determine if the default value will be formatted on page ready.
                         * true = automatically formats the default value on page ready
                         * false = will not format the default value
                         */
                        aForm: true,
                        /** determine if the select all keyboard command will select the complete input text or only the input numeric value" */
                        sNumber: false,
                        /** future use */
                        onSomeEvent: function () {}
                    };
                    settings = $.extend({}, defaults, tagData, options); /** Merge defaults, tagData and options */
                    $this.data('autoNumeric', settings); /** Save our new settings */
                } else {
                    return this;
                }
                settings = originalSettings(settings); /** original settings saved for use when eDec & nSep options are being used */
                settings.rawValue = '';
                settings.runOnce = false;
                var holder = getHolder($this, settings);
                if (!$this.is('input[type=text], input[type=hidden], input[type=tel], input:not([type])') && $this.prop('tagName').toLowerCase() === 'input') { /** checks for non-supported input types */
                    $.error('The input type "' + $this.prop('type') + '" is not supported by autoNumeric()');
                    return this;
                }
                if ($.inArray($this.prop('tagName').toLowerCase(), settings.tagList) === -1 && $this.prop('tagName').toLowerCase() !== 'input') { /** checks for non-supported tags */
                    $.error("The <" + $this.prop('tagName').toLowerCase() + "> is not supported by autoNumeric()");
                    return this;
                }
                if (settings.aDec === settings.aSep) { /** checks if the decimal and thousand are characters are the same */
                    $.error("autoNumeric will not function properly when the decimal character aDec: '" + settings.aDec + "' and thousand separator aSep: '" + settings.aSep + "' are the same character");
                    return this;
                }
                if (settings.eDec < settings.mDec && settings.eDec !== null) { /** checks the extended decimal places "eDec" is greater than the normal decimal places "mDec" */
                    $.error("autoNumeric will not function properly when the extended decimal places 'eDec: '" + settings.eDec + "' is greater than mDec: '" + settings.mDec + "' value");
                    return this;
                }
                if (settings.runOnce === false && settings.aForm) {/** routine to format default value on page load */
                    if ($this.is('input[type=text], input[type=hidden], input[type=tel], input:not([type])')) {
                        var setValue = true;
                        /** routine to handle page re-load from back button & saves the raw value to settings.rawValue */
                        if ($this.val() !== '' && $this.val() !== $this.attr('value')) {
                            if (settings.eDec !== null && settings.aStor) {
                                if (autoSave($this, settings, 'get') !== null) {
                                    $this.autoNumeric('set', autoSave($this, settings, 'get'));
                                }
                                setValue = false;
                            }
                            if (settings.eDec !== null && settings.aStor === false || autoSave($this, settings, 'get') === null) {
                                $this.autoNumeric('set', autoStrip($this.val(), settings).replace(',', '.'));
                                setValue = false;
                            }
                        }
                        if ($this.val() === '' && settings.wEmpty === 'empty') {
                            $this.val('');
                            setValue = false;
                        }
                        if ($this.val() === '' && settings.wEmpty === 'sign') {
                            $this.val(settings.aSign);
                            setValue = false;
                        }
                        if (setValue) {
                            $this.autoNumeric('set', $this.val());
                        }
                    }
                }
                if ($.inArray($this.prop('tagName').toLowerCase(), settings.tagList) !== -1 && $this.text() !== '') {
                    $this.autoNumeric('set', $this.text());
                }
                settings.runOnce = true;
                if ($this.is('input[type=text], input[type=hidden], input[type=tel], input:not([type])')) { /**input types supported "text", "hidden" and no type*/
                    $this.on('keydown.autoNumeric', function (e) {
                        holder = getHolder($this);
                        if (holder.that.readOnly) {
                            holder.processed = true;
                            return true;
                        }
                        /** The below streamed code / comment allows the "enter" keydown to throw a change() event */
                        /** if (e.keyCode === 13 && holder.inVal !== $this.val()){
                            $this.change();
                            holder.inVal = $this.val();
                        }*/
                        holder.init(e);
                        if (holder.skipAllways(e)) {
                            holder.processed = true;
                            return true;
                        }
                        if (holder.processAllways()) {
                            holder.processed = true;
                            holder.formatQuick();
                            e.preventDefault();
                            return false;
                        }
                        holder.formatted = false;
                        return true;
                    });
                    $this.on('keypress.autoNumeric', function (e) {
                        var holder = getHolder($this),
                            processed = holder.processed;
                        holder.init(e);
                        if (holder.skipAllways(e)) {
                            return true;
                        }
                        if (processed) {
                            e.preventDefault();
                            return false;
                        }
                        if (holder.processAllways() || holder.processKeypress()) {
                            holder.formatQuick();
                            e.preventDefault();
                            return false;
                        }
                        holder.formatted = false;
                    });
                    $this.on('keyup.autoNumeric', function (e) {
                        var holder = getHolder($this);
                        holder.init(e);
                        var skip = holder.skipAllways(e);
                        holder.kdCode = 0;
                        delete holder.valuePartsBeforePaste;
                        if ($this[0].value === holder.settingsClone.aSign) { /** added to properly place the caret when only the currency is present */
                            if (holder.settingsClone.pSign === 's') {
                                setElementSelection(this, 0, 0);
                            } else {
                                setElementSelection(this, holder.settingsClone.aSign.length, holder.settingsClone.aSign.length);
                            }
                        }
                        if ($this[0].value === holder.settingsClone.aSuffix) {
                            setElementSelection(this, 0, 0);
                        }
                        if (holder.settingsClone.rawValue === '' && holder.settingsClone.aSign !== '' && holder.settingsClone.aSuffix !== '') {
                            setElementSelection(this, 0, 0);
                        }
                        if (holder.settingsClone.eDec !== null) { /** saves the extended decimal to preserve the data when navigating away from the page */
                            autoSave($this, settings, 'set');
                        }
                        if (skip) {
                            return true;
                        }
                        if (this.value === '') {
                            return true;
                        }
                        if (!holder.formatted) {
                            holder.formatQuick();
                        }
                    });
                    $this.on('focusin.autoNumeric', function () {
                        var holder = getHolder($this),
                            $settings = holder.settingsClone;
                        $settings.oEvent = 'focusin';
                        if ($settings.nBracket !== null) {
                            var checkVal = $this.val();
                            $this.val(negativeBracket(checkVal, $settings.nBracket, $settings.oEvent, $settings.aSuffix, $settings.pNeg));
                        }
                        if ($settings.nSep === true) {

                            $settings.aSep = '';
                            $settings.aSign = '';
                        }
                        if ($settings.eDec !== null) {
                            $settings.mDec = $settings.eDec;
                            $settings.aPad = false;
                            $settings.nBracket = null;
                            $this.autoNumeric('set', $settings.rawValue);
                        } else {
                            $this.autoNumeric('set', $settings.rawValue);
                        }
                        holder.inVal = $this.val();
                        var onEmpty = checkEmpty(holder.inVal, $settings, true);
                        if (onEmpty !== null && onEmpty !== '') {
                            $this.val(onEmpty);
                        }
                        $settings.oEvent = null;
                    });
                    $this.on('focusout.autoNumeric', function () {
                        var holder = getHolder($this),
                            value = $this.val(),
                            origValue = value,
                            $settings = holder.settingsClone;
                        $settings.oEvent = 'focusout';
                        if ($settings.eDec !== null) {
                            autoSave($this, $settings, 'set');
                        }
                        var strip_zero = ''; /** added to control leading zero */
                        if ($settings.lZero === 'allow') { /** added to control leading zero */
                            $settings.allowLeading = false;
                            strip_zero = 'leading';
                        }
                        if ($settings.nSep === true) {
                            $settings.aSep = $settings.oSep;
                            $settings.aSign = $settings.oSign;
                        }
                        if ($settings.eDec !== null) {
                            $settings.mDec = $settings.oDec;
                            $settings.aPad = $settings.oPad;
                            $settings.nBracket = $settings.oBracket;
                        }
                        if (value !== '') {
                            value = autoStrip(value, $settings, strip_zero);


                            if (checkEmpty(value, $settings) === null && autoCheck(value, $settings, $this[0])) {
                                value = fixNumber(value, $settings.aDec, $settings.aNeg);
                                value = autoRound(value, $settings);
                                value = presentNumber(value, $settings.aDec, $settings.aNeg);
                            } else {
                                value = '';
                            }
                        }
                        var groupedValue = checkEmpty(value, $settings, false);
                        if (groupedValue === null) {
                            groupedValue = autoGroup(value, $settings);
                        }
                        if (groupedValue !== origValue) {
                            $this.val(groupedValue);
                        }
                        if (groupedValue !== holder.inVal) {
                            $this.change();
                            delete holder.inVal;
                        }
                        if ($settings.nBracket !== null && $settings.rawValue < 0) {
                            $this.val(negativeBracket($this.val(), $settings.nBracket, $settings.oEvent, $settings.aSuffix, $settings.pNeg));
                        }
                        $settings.oEvent = null;
                    });
                }
            });
        },
        /** method to remove settings and stop autoNumeric() */
        destroy: function () {
            return $(this).each(function () {
                var $this = autoGet($(this)),
                    settings = $this.data('autoNumeric');
                $this.val('');
                autoSave($this, settings, 'wipe');
                $this.off('.autoNumeric');
                $this.removeData('autoNumeric');
            });
        },
        /** method to clear the value and sessionStorage */
        wipe: function () {
            return $(this).each(function () {
                var $this = autoGet($(this)),
                    settings = $this.data('autoNumeric');
                if (typeof settings !== 'object') {
                    $.error("You must initialize autoNumeric('init', {options}) prior to calling the 'wipe' method");
                    return this;
                }
                $this.val('');
                settings.rawValue = '';
                autoSave($this, settings, 'wipe');
            });
        },
        /** method to update settings - can call as many times */
        update: function (options) {
            return $(this).each(function () {
                var $this = autoGet($(this)),
                    settings = $this.data('autoNumeric');
                if (typeof settings !== 'object') {
                    $.error("You must initialize autoNumeric('init', {options}) prior to calling the 'update' method");
                    return this;
                }
                var strip = $this.autoNumeric('get');
                settings = $.extend(settings, options);
                settings = originalSettings(settings); /** update original settings */
                getHolder($this, settings, true);
                if (settings.aDec === settings.aSep) {
                    $.error("autoNumeric will not function properly when the decimal character aDec: '" + settings.aDec + "' and thousand separator aSep: '" + settings.aSep + "' are the same character");
                    return this;
                }
                $this.data('autoNumeric', settings);
                if ($this.val() !== '' || $this.text() !== '') {
                    return $this.autoNumeric('set', strip);
                }
                return;
            });
        },
        /** returns a formatted strings for "input:text" fields Uses jQuery's .val() method*/
        set: function (valueIn) {
            return $(this).each(function () {
                if (valueIn === null) {
                    return;
                }
                var $this = autoGet($(this)),
                    settings = $this.data('autoNumeric'),
                    value = valueIn.toString(),
                    testValue = valueIn.toString(),
                    $input = $this.is('input[type=text], input[type=hidden], input[type=tel], input:not([type])');
                    testValue = valueIn.toString();
                if (typeof settings !== 'object') {
                    $.error("You must initialize autoNumeric('init', {options}) prior to calling the 'set' method");
                    return false;
                }
                /** allows locale decimal separator to be a comma */
                value = value.replace(',', '.');
                /** Throws an error if the value being set is not numeric */
               // console.log(" value "+value)
                if (!$.isNumeric(+value)) {
                    $.error("The value being 'set' is not numeric that has caused a error to be thrown");
                    return;
                }
                value = checkValue(value, settings);
                if (settings.oEvent !== 'focusin') {
                    settings.oEvent = 'set';
                }
                value.toString();
                if (value !== '') {
                    if (settings.eDec !== null) {
                        settings.rawValue = value;
                    }
                    value = autoRound(value, settings);
                    if (settings.eDec === null) {
                        settings.rawValue = value;
                    }
                    if ($input && settings.eDec !== null) {
                        autoSave($this, settings, 'set');
                    }
                }
                value = presentNumber(value, settings.aDec, settings.aNeg);
                if (autoCheck(testValue, settings) === false) { /** checks if the value falls within the min max range */
                    $this.val('');
                    settings.rawValue = '';
                    autoSave($this, settings, 'wipe');
                    $.error("The value (" + value + ") being 'set' falls outside of the vMin / vMax range");
                    return false;
                }
                value = autoGroup(value, settings);
                if ($input) {
                    return $this.val(value);
                }
                if ($.inArray($this.prop('tagName').toLowerCase(), settings.tagList) !== -1) {
                    return $this.text(value);
                }
                $.error("The <" + $this.prop('tagName').toLowerCase() + "> is not supported by autoNumeric()");
                return false;
            });
        },
        /** returns a formatted strings for "input:text" fields Uses jQuery's .val() method*/
        reSet: function () {
            return $(this).each(function () {
                var $this = autoGet($(this)),
                    settings = $this.data('autoNumeric');
                if (typeof settings !== 'object') {
                    $.error("You must initialize autoNumeric('init', {options}) prior to calling the 'unSet' method");
                    return this;
                }
                $this.autoNumeric('set', $this.val());
            });
        },
        /** returns a formatted strings for "input:text" fields Uses jQuery's .val() method*/
        unSet: function (outPut) {
            return $(this).each(function () {
                var $this = autoGet($(this)),
                    settings = $this.data('autoNumeric');
                if (typeof settings !== 'object') {
                    $.error("You must initialize autoNumeric('init', {options}) prior to calling the 'unSet' method");
                    return this;
                }
                if (typeof settings === 'object') {
                    $this.val($this.autoNumeric('get', outPut));
                }
            });
        },
        /** method to get the unformatted value from a specific input field, returns a numeric value */
        get: function (outPut) {
            var $this = autoGet($(this)),
                settings = $this.data('autoNumeric');
            if (typeof settings !== 'object') {
                $.error("You must initialize autoNumeric('init', {options}) prior to calling the 'get' method");
                return false;
            }
            var getValue = '';
            /** determine the element type then use .eq(0) selector to grab the value of the first element in selector */
            if ($this.is('input[type=text], input[type=hidden], input[type=tel], input:not([type])')) {
                getValue = $this.eq(0).val();
            } else if ($.inArray($this.prop('tagName').toLowerCase(), settings.tagList) !== -1) {
                getValue = $this.eq(0).text();
            } else {
                $.error("The <" + $this.prop('tagName').toLowerCase() + "> is not supported by autoNumeric()");
                return false;
            }
            if (settings.rawValue !== '') {
                getValue = settings.rawValue;
            }
            if ((getValue === '' && settings.wEmpty === 'empty') || (getValue === settings.aSign && (settings.wEmpty === 'sign' || settings.wEmpty === 'empty'))) {
                return '';
            }
            settings.oEvent = 'get';
            if (settings.nBracket !== null && getValue !== '') {
                getValue = negativeBracket(getValue, settings.nBracket, settings.oEvent, settings.aSuffix, settings.pNeg);
            }
            getValue = fixNumber(getValue, settings.aDec, settings.aNeg);
            if (+getValue === 0 && settings.lZero !== 'keep') {
                getValue = '0';
            }
            getValue = checkValue(getValue, settings);
            if (outPut === 'asKomma') {
                getValue = getValue.replace('.', ',');
            }
            if (outPut === 'checkOptions' && settings.aDec === ',') {
                getValue = getValue.replace('.', ',');
            }
            return getValue; /** returned Numeric String */
        },
        /** method to get the unformatted value from multiple fields */
        getString: function (outPut) {
            var isAutoNumeric = false,
                $this = autoGet($(this)),
                str = $this.serialize(),
                parts = str.split('&'),
                formIndex = $('form').index($this),
				i = 0;
			for (i; i < parts.length; i += 1) {
                var miniParts = parts[i].split('='),
					$field = $('form:eq(' + formIndex + ') input[name="' + decodeURIComponent(miniParts[0]) + '"]'),
                    settings = $field.data('autoNumeric');
                if (typeof settings === 'object') {
                    if (miniParts[1] !== null) {
                        miniParts[1] = $field.autoNumeric('get', outPut);
                        parts[i] = miniParts.join('=');
                        isAutoNumeric = true;
                    }
                }
            }
            if (isAutoNumeric === true) {
                return parts.join('&');
            }
            return str;
        },
        /** method to get the unformatted value from multiple fields */
        getArray: function (outPut) {
            var isAutoNumeric = false,
                $this = autoGet($(this)),
                formFields = $this.serializeArray(),
				formIndex = $('form').index($this);
            /*jslint unparam: true*/
            $.each(formFields, function (i, field) {
				var $field = $('form:eq(' + formIndex + ') input[name="' + decodeURIComponent(field.name) + '"]'),
                    settings = $field.data('autoNumeric');
                if (typeof settings === 'object') {
                    if (field.value !== '') {
                        field.value = $field.autoNumeric('get', outPut).toString();
                    }
                    isAutoNumeric = true;
                }
            });
            /*jslint unparam: false*/
            if (isAutoNumeric === true) {
                return formFields;
            }
            $.error("You must initialize autoNumeric('init', {options}) prior to calling the 'getArray' method");
            return this;
        },
        /** returns the settings object for those who need to look under the hood */
        getSettings: function () {
            var $this = autoGet($(this));
            return $this.eq(0).data('autoNumeric');
        }
    };
    $.fn.autoNumeric = function (method) {
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        }
        if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        }
        $.error('Method "' + method + '" is not supported by autoNumeric()');
    };
}(jQuery, window, document));
