export interface HttpClientError {
    error: BackendException;
    message: string;
    status: number;
    statusText: string;
    url: string;
    name: string;
    headers: any;
}

interface BackendException {
    cause: string;
    error: string;
    type: string;
    class: string;
}
