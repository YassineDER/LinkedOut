import{e as a}from"./chunk-VCVIXHJE.js";import{C as c,F as p,hb as l}from"./chunk-L2MPYLPP.js";var u=(()=>{let t=class t{constructor(e){this.http=e,this.url=a.hostUrl+"/api/social"}isConnection(e){let i=e.profileId;return new Promise((n,s)=>{this.http.get(this.url+"/profiles/connected/check",{params:{profile_id:i}}).subscribe({next:r=>n(r),error:r=>s(r)})})}connect(e){let i=e.profileId;return new Promise((n,s)=>{this.http.post(this.url+"/connect",null,{params:{profile_id:i}}).subscribe({next:r=>n(r),error:r=>s(r)})})}};t.\u0275fac=function(i){return new(i||t)(p(l))},t.\u0275prov=c({token:t,factory:t.\u0275fac});let o=t;return o})();export{u as a};
