$(document).ready(function () {
});

$.extend({
    distinct: function (anArray) {
        var result = [];
        $.each(anArray, function (i, v) {
            if ($.inArray(v, result) == -1)
                result.push(v);
        });
        return result;
    },
    hasEmptyElement: function (anArray) {
        return ($.inArray("", anArray) > -1);
    }
});

function planogram(e, answerId) {
    var img = $(e).parent().parent().next().find('img');
    var src = img.attr('src');
    var alt = img.attr('alt');
    var title = img.attr('title');
    var width = img.attr('width');
    var height = img.attr('height');
    $('#item' + e.value).html($('<img>', {id: 'image' + answerId, src: src, alt: alt, title: title, width: width, height: height, style: 'width: ' + width + 'px; height: ' + height + 'px;'}));
//    checkOnePlanogram(e);
//    checkPlanogram();
}

function checkOnePlanogram(e) {
    var inputs = $(":input.ui-inputfield");

    inputs.removeClass("ui-state-error");
    inputs.filter(function (i, el) {
        return inputs.not(this).filter(function () {
            return this.value === el.value;
        }).length !== 0;
    }).addClass("ui-state-error");

    var inputValues = inputs.not($(e)).map((i, el) => $(el).val());
    $(e).removeClass("ui-state-error");
    if ($.inArray(e.value, inputValues) > -1) {
        $(e).addClass("ui-state-error");
        return false;
    }
    return true;
}

function checkPlanogram() {
    var inputs = $(":input.ui-inputfield");
    var inputValues = inputs.map(function () {
        return $(this).val();
    }).toArray();
    var unique = $.distinct(inputValues);
    var hasEmptyElement = $.hasEmptyElement(inputValues);

    if (hasEmptyElement) {
        alert("You can not leave it blank");
        return false;
    } else if (inputValues.length != unique.length) {
        alert("You can not enter the same slot number");
        return false;
    } else if (inputValues.length == unique.length && hasEmptyElement == false) {
        return true;
    }
}

function handleSubmitRequest(xhr, status, args, dialogId, formId, dialogWidgetVar) {
    if (args.validationFailed) {
        PF(dialogWidgetVar).jq.effect("shake", {times: 5}, 100);
    } else {
        clearForm(formId);
        PF(dialogWidgetVar).hide();
    }
}

function clearForm(formId) {
    jQuery('#' + formId).each(function () {
        this.reset();
    });
}


/**
 * https://code.google.com/p/primefaces/wiki/PrimeFacesLocales
 */
PrimeFaces.locales['tr_TR'] = {
    closeText: 'Kapat',
    prevText: 'Geri',
    nextText: 'İleri',
    currentText: 'Bugün',
    monthNames: ['Ocak', 'Şubat', 'Mart', 'Nisan', 'Mayıs', 'Haziran', 'Temmuz', 'Ağustos', 'Eylül', 'Ekim', 'Kasım', 'Aralık'],
    monthNamesShort: ['Oca', 'Şub', 'Mar', 'Nis', 'May', 'Haz', 'Tem', 'Ağu', 'Eyl', 'Eki', 'Kas', 'Ara'],
    dayNames: ['Pazar', 'Pazartesi', 'Salı', 'Çarşamba', 'Perşembe', 'Cuma', 'Cumartesi'],
    dayNamesShort: ['Pz', 'Pt', 'Sa', 'Ça', 'Pe', 'Cu', 'Ct'],
    dayNamesMin: ['Pz', 'Pt', 'Sa', 'Ça', 'Pe', 'Cu', 'Ct'],
    weekHeader: 'Hf',
    firstDay: 1,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: '',
    month: 'Ay',
    week: 'Hafta',
    day: 'Gün',
    allDayText: 'Tüm Gün'
};
