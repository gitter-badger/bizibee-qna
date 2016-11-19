$(document).ready(function () {
    $('#menu-button').on('click', function (e) {
        $('#menu').toggleClass('overlay-menu');
        e.preventDefault();
    });
    
    $('.popup-video').magnificPopup({
        disableOn: 700,
        type: 'iframe',
        mainClass: 'mfp-fade',
        removalDelay: 160,
        preloader: false,
        fixedContentPos: false
    });
});

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
