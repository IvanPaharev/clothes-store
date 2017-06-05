'use strict';

angular.module('myApp').controller('languageController', ['$rootScope', 'languageService',
    function($rootScope, languageService) {

        var self = this;

        self.currentLanguage = "en";
        self.availableLanguages = ["en", "ru"];
        self.fetchMessages = fetchMessages;

        function fetchMessages() {
            languageService.fetchMessages(self.currentLanguage)
                .then(
                    function (messages) {
                        $rootScope.i18nMessages = messages;
                    }
                );
        }
    }]);
