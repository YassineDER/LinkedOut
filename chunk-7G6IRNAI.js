import{Pa as u,g as s,w as n,z as c}from"./chunk-JPZ7LBG3.js";var a=function(t){return t.DEFAULT="default",t.SUCCESS="success",t.WARNING="warning",t.ERROR="error",t}(a||{});var i=function(t){return t.DEFAULT="default",t.POST="post",t}(i||{});var y=(()=>{let r=class r{constructor(e){this.http=e,this.alertSubject=new s(null),this.alert$=this.alertSubject.asObservable(),this.ip$=null,this.audio=new Audio}alert(e,o=a.DEFAULT){this.alertSubject.next({message:e,type:o})}playSound(e){let o="";switch(e){case i.DEFAULT:o="assets/sound/notification.wav";break;case i.POST:o="assets/sound/post.wav";break}this.audio.src=o,this.audio.load(),this.audio.play()}formatDate(e){let[o,p,h,d,f,l,m]=e;return new Date(o,p-1,h,d,f,l,m/1e6).toLocaleString()}fetchIp(){return this.http.get("https://api.ipify.org?format=json")}get ip(){return this.ip$}set ip(e){this.ip$=e}};r.\u0275fac=function(o){return new(o||r)(c(u))},r.\u0275prov=n({token:r,factory:r.\u0275fac,providedIn:"root"});let t=r;return t})();export{a,i as b,y as c};
