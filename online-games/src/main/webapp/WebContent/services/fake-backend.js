
// setup fake backend for backend-less development

app.run(function ($httpBackend) {
//    var testUser = { username: 'test', password: 'test', firstName: 'Test', lastName: 'User' };

    // fake authenticate api end point
    $httpBackend.whenPOST(/^\w+.*/).passThrough();
    // $httpBackend.whenPOST('/authenticate').respond(function (method, url, data) {
        // get parameters from post request
        // var params = angular.fromJson(data);

        // // check user credentials and return fake jwt token if valid
        // if (params.username === testUser.username && params.password === testUser.password) {
        //     return [200, { token: 'fake-jwt-token' }, {}];
        // } else {
        //     return [200, {}, {}];
        // }
    // });

    // pass through any urls not handled above so static files are served correctly
    $httpBackend.whenGET(/^\w+.*/).passThrough();

    $httpBackend.whenDELETE(/^\w+.*/).passThrough();
});
