$(document).ready(function () {
});

function planogram(e, slotId, answerId) {
    var img = $(e).parent().parent().next().find('img');
    var src = img.attr('src');
    var alt = img.attr('alt');
    var title = img.attr('title');
    var width = img.attr('width');
    var height = img.attr('height');
    $('#item' + slotId).html($('<img>', {id: 'image' + answerId, src: src, alt: alt, title: title, width: width, height: height, style: 'width: ' + width + 'px; height: ' + height + 'px;'}));

    var inputs = $(":input.ui-inputfield");
    inputs.removeClass("ui-state-error");
    inputs.filter(function (i, el) {
        return inputs.not(this).filter(function () {
            return this.value === el.value;
        }).length !== 0;
    }).addClass("ui-state-error");
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
