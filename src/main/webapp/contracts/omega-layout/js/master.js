$(document).ready(function () {
    $('#menu-button').on('click', function (e) {
        $('#menu').toggleClass('overlay-menu');
        e.preventDefault();
    });
});

function planogram(slotId, skuId, skuName) {
//    alert(skuId);
//    alert(slotId);
//    $('#item'+slotId).html(skuId);
//    $('#item'+slotId).html(skuName);
    $('#item' + slotId).html($('<img>', {id: 'image' + skuId, src: 'data:image/png;base64,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'}));
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
