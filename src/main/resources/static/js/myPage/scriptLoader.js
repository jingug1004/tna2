
var scriptLoader = function(id, src, n4TimeoutMS, isWriteDocument) {
    var self = this;
    this.isWriteDocument = isWriteDocument;
    this.id = id;
    this.src = src;
    this.n4TimeoutMS = n4TimeoutMS || 3000;
    this.timer = null;
    this.successCallback = function () {
    };
    this.failCallback = function () {
    };
    this.scriptObject = null;
    var startTimer = function(){
        return setTimeout(function(){
            var msg = 'Script load timed out '+self.n4TimeoutMS+' ms, src:'+self.src;
            self.scriptObject.src= '';
            try{
                //self.scriptObject.remove();
                self.scriptObject.parentNode.removeChild(self.scriptObject);//cause IE
            }catch(e){
                console.log(e);
            }
            self.onLoad = function(){};
            self.log(msg);
            self.failCallback(self);
        }, self.n4TimeoutMS);
    };
    this.start= function(){
        self.log('start : '+self.src);
        if(self.isWriteDocument === true){
            var tagString = "<script type='text/javascript' id='"+self.id+"' src='"+self.src+"' ></script>";
            document.write(tagString);
            self.scriptObject = document.getElementById(self.id);
        }else{
            self.scriptObject = document.createElement('script');
            self.scriptObject.id = self.id;
            self.scriptObject.setAttribute('src',self.src);
            document.body.appendChild(self.scriptObject);
        }
        self.scriptObject.onload = function(e){
            self.onLoad(e);
        };
        self.timer = startTimer();
        return self;
    };
    this.onLoad = function (e) {
        clearTimeout(self.timer);
        self.log('loaded ' + self.src);
        self.successCallback(self);
    };
    this.fail = function (callback) {
        self.failCallback = callback;
        return self;
    };
    this.success = function (callback) {
        self.successCallback = callback;
        return self;
    };
    this.log = function (msg) {
        console.log('[scriptLoader:' + self.id + '] ' + msg);
    };
};
